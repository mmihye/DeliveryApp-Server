package com.example.deliveryapp.domain.reservation.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.deliveryapp.domain.reservation.entity.OrderReservation;
import com.example.deliveryapp.domain.reservation.enumerate.OrderReservationStatus;
import com.example.deliveryapp.domain.reservation.repository.OrderReservationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationScheduling {
	private final OrderReservationRepository orderReservationRepository;
	private final OrderReservationService orderReservationService;
	private final RedissonClient redissonClient;

	private static final String LOCK_KEY = "processReservedOrderLock";

	@Scheduled(cron = "0 0/10 * * * *", zone = "Asia/Seoul")
	public void processReservedOrder() {
		RLock lock = redissonClient.getLock(LOCK_KEY);
		boolean isLocked = false;

		try {
			isLocked = lock.tryLock(5, 30, TimeUnit.SECONDS);
			if (!isLocked) {
				log.info("다른 인스턴스가 이미 실행 중이므로 종료");
				return;
			}

			log.info("주문 예약 처리 시작");

			LocalDateTime time = LocalDateTime.now().withNano(0);
			List<OrderReservation> reservations = orderReservationRepository.findByStatusAndReservationTimeLessThanEqual(
				OrderReservationStatus.PENDING, time);

			reservations.forEach(reservation -> {
				try {
					orderReservationService.updateReservationStatus(reservation);
					// TODO: 주문 처리 로직 추가
					log.info("예약 주문 처리 완료: {}", reservation.getId());
				} catch (Exception e) {
					log.error("주문예약 작업 실패 ID {}: {}", reservation.getId(), e.getMessage(), e);
				}
			});

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			log.error("Lock 획득 시도 중 인터럽트 발생", e);
		} finally {
			if (isLocked && lock.isHeldByCurrentThread()) {
				lock.unlock();
				log.info("락 해제 완료");
			}
		}
	}

}
