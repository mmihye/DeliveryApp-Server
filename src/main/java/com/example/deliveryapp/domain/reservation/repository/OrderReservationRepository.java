package com.example.deliveryapp.domain.reservation.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.deliveryapp.domain.reservation.entity.OrderReservation;

public interface OrderReservationRepository extends JpaRepository<OrderReservation, Long> {

	List<OrderReservation> findByReservationTime(LocalDateTime reservationTime);
}
