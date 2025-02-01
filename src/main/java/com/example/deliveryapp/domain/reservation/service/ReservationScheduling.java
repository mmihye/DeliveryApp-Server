package com.example.deliveryapp.domain.reservation.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Scheduled(cron = "* 0/30 * * * *", zone = "Asia/Seoul")
	@Transactional
	public void processReservedOrder() {
		LocalDateTime time =LocalDateTime.now().withNano(0);

		List<OrderReservation> reservations = orderReservationRepository.findByReservationTime(time);
		reservations.forEach((reservation) ->
			reservation.updateStatus(OrderReservationStatus.PROCESSING)
		);

		//todo 주문처리 로직
	}
}
