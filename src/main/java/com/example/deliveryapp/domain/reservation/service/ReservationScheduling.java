package com.example.deliveryapp.domain.reservation.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.deliveryapp.domain.reservation.entity.OrderReservation;
import com.example.deliveryapp.domain.reservation.repository.OrderReservationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationScheduling {
	private final OrderReservationRepository orderReservationRepository;
	private final OrderReservationService orderReservationService;

	@Scheduled(cron = "* 0/30 * * * *", zone = "Asia/Seoul")
	public void processReservedOrder() {
		LocalDateTime time = LocalDateTime.now().withNano(0);
		List<OrderReservation> reservations = orderReservationRepository.findByReservationTime(time);

		reservations.forEach(reservation -> {
			try {
				orderReservationService.updateReservationStatus(reservation);
				//todo 주문처리 로직
			} catch (Exception e) {
				log.error("주문예약 작업 실패 ID {}: {}", reservation.getId(), e.getMessage(), e);
			}
		});
	}

}
