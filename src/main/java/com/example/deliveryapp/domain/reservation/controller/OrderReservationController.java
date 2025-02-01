package com.example.deliveryapp.domain.reservation.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.deliveryapp.domain.reservation.dto.Param.ReserveOrderParam;
import com.example.deliveryapp.domain.reservation.dto.Request.ReserveOrderReq;
import com.example.deliveryapp.domain.reservation.service.OrderReservationService;
import com.example.deliveryapp.domain.store.dto.Param.CreateStoreParam;
import com.example.deliveryapp.global.common.ApiResponse;
import com.example.deliveryapp.global.common.LoginUser;
import com.example.deliveryapp.global.exception.Success;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/reservations")
public class OrderReservationController {
	private final OrderReservationService service;
	@PostMapping("/orders")
	public ApiResponse<?> reserveOrder(
		@RequestBody @Valid ReserveOrderReq reserveOrderReq,
		@LoginUser String email
	) {
		service.reserve(ReserveOrderParam.from(reserveOrderReq,email));
		return ApiResponse.success(Success.CREATE_SUCCESS);
	}

	@DeleteMapping("/orders/{reservationId}")
	public ApiResponse<?> cancel(
		@PathVariable Long reservationId,
		@LoginUser String email
	) {
		service.cancel(reservationId, email);
		return ApiResponse.success(Success.SUCCESS);
	}

}
