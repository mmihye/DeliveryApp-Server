package com.example.deliveryapp.domain.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.deliveryapp.domain.reservation.entity.OrderReservation;

public interface OrderReservationRepository extends JpaRepository<OrderReservation, Long> {
}
