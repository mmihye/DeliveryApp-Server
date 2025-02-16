package com.example.deliveryapp.domain.reservation.service;

import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.deliveryapp.domain.menu.entity.Menu;
import com.example.deliveryapp.domain.menu.repository.MenuRepository;
import com.example.deliveryapp.domain.order.entity.Order;
import com.example.deliveryapp.domain.order.entity.OrderMenu;
import com.example.deliveryapp.domain.order.repository.OrderMenuRepository;
import com.example.deliveryapp.domain.order.repository.OrderRepository;
import com.example.deliveryapp.domain.reservation.dto.Param.ReserveOrderParam;
import com.example.deliveryapp.domain.reservation.entity.OrderReservation;
import com.example.deliveryapp.domain.reservation.enumerate.OrderReservationStatus;
import com.example.deliveryapp.domain.reservation.repository.OrderReservationRepository;
import com.example.deliveryapp.domain.store.service.StoreService;
import com.example.deliveryapp.domain.user.entity.User;
import com.example.deliveryapp.domain.user.module.UserModule;
import com.example.deliveryapp.global.exception.ApplicationException;
import com.example.deliveryapp.global.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderReservationService {

	private final OrderReservationRepository orderReservationRepository;
	private final UserModule userModule;
	private final StoreService storeService;
	private final OrderRepository orderRepository;
	private final MenuRepository menuRepository;
	private final OrderMenuRepository orderMenuRepository;


	@Transactional
	public void reserve(
		ReserveOrderParam param
	) {
		User user = userModule.findUserByEmail(param.userEmail());

		Order order = Order.builder()
			.address(param.address())
			.riderRequest(param.riderRequest())
			.storeRequest(param.storeRequest())
			.store(storeService.getStore(param.storeId()))
			.user(user)
			.build();

		OrderReservation newReservation = OrderReservation.builder()
			.reservationTime(param.reservationTime())
			.user(user)
			.order(order).build();

		orderRepository.save(order);
		saveOrderMenus(order, param.menuList());
		orderReservationRepository.save(newReservation);
	}

	private void saveOrderMenus(Order order, List<Long> menuIds) {
		List<Menu> menuList = menuRepository.findByIdIn(menuIds);
		List<OrderMenu> orderMenuList = menuList.stream().map(menu -> new OrderMenu(order, menu)).toList();

		orderMenuRepository.saveAll(orderMenuList);
	}

	public void cancel(Long reservationId, String email) {
		OrderReservation reservation = orderReservationRepository.findById(reservationId)
			.orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_EXCEPTION));

		if (!reservation.getUser().getEmail().equals(email)) {
			throw new ApplicationException(ErrorCode.FORBIDDEN_EXCEPTION);
		}

		orderReservationRepository.deleteById(reservationId);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateReservationStatus(OrderReservation reservation) {
		reservation.updateStatus(OrderReservationStatus.PROCESSING);
		orderReservationRepository.save(reservation);
	}
}
