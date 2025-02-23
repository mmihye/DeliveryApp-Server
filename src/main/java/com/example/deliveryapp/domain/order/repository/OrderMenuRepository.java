package com.example.deliveryapp.domain.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.deliveryapp.domain.order.entity.OrderMenu;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, Long> {
}