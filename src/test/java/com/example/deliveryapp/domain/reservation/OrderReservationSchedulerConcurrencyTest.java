package com.example.deliveryapp.domain.reservation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.deliveryapp.domain.reservation.repository.OrderReservationRepository;
import com.example.deliveryapp.domain.reservation.service.OrderReservationService;
import com.example.deliveryapp.domain.reservation.service.ReservationScheduling;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@EnableScheduling
public class OrderReservationSchedulerConcurrencyTest {

	@Autowired
	private ReservationScheduling orderReservationScheduler;

	@MockBean
	private OrderReservationRepository orderReservationRepository;

	@MockBean
	private OrderReservationService orderReservationService;

	@MockBean
	private RedissonClient redissonClient;

	@MockBean
	private RLock lock;

	@Test
	void testProcessReservedOrder_ConcurrentExecution() throws InterruptedException {
		// given
		when(redissonClient.getLock(any())).thenReturn(lock);

		AtomicBoolean isFirstLock = new AtomicBoolean(true);
		when(lock.tryLock(5, 30, TimeUnit.SECONDS)).thenAnswer(invocation -> isFirstLock.getAndSet(false));

		AtomicInteger executionCount = new AtomicInteger(0);

		doAnswer(invocation -> {
			executionCount.incrementAndGet();
			Thread.sleep(500);
			return null;
		}).when(orderReservationRepository).findByStatusAndReservationTimeLessThanEqual(any(), any());

		ExecutorService executorService = Executors.newFixedThreadPool(5);
		CountDownLatch latch = new CountDownLatch(5);

		// when: 5개의 스레드가 동시에 실행을 시도
		for (int i = 0; i < 5; i++) {
			executorService.submit(() -> {
				try {
					orderReservationScheduler.processReservedOrder();
				} finally {
					latch.countDown();
				}
			});
		}

		latch.await();
		executorService.shutdown();
	}
}
