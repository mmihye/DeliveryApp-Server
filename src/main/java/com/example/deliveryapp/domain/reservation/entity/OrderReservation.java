package com.example.deliveryapp.domain.reservation.entity;
import java.time.LocalDateTime;

import org.aspectj.weaver.ast.Or;

import com.example.deliveryapp.domain.order.entity.Order;
import com.example.deliveryapp.domain.reservation.enumerate.OrderReservationStatus;
import com.example.deliveryapp.domain.store.entity.Store;
import com.example.deliveryapp.domain.user.entity.User;
import com.example.deliveryapp.global.common.BaseEntity;
import com.example.deliveryapp.global.common.BaseTimeEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderReservation extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private LocalDateTime reservationTime;

	private OrderReservationStatus status;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "order_id")
	private Order order;

	@Builder
	public OrderReservation(LocalDateTime reservationTime, Order order, User user) {
		this.reservationTime = reservationTime;
		this.order = order;
		this.user = user;
		this.status = OrderReservationStatus.PENDING;
	}

	public void updateStatus(OrderReservationStatus status){
		this.status = status;
	}
}
