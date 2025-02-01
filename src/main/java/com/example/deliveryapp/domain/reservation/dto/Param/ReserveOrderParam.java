package com.example.deliveryapp.domain.reservation.dto.Param;

import java.time.LocalDateTime;
import java.util.List;

import com.example.deliveryapp.domain.reservation.dto.Request.ReserveOrderReq;

public record ReserveOrderParam(
	String userEmail,
	List<Long> menuList,
	String address,
	LocalDateTime reservationTime,
	String storeRequest,
	String riderRequest,
	Long storeId
) {
	public static ReserveOrderParam from(ReserveOrderReq request, String userEmail) {
		return new ReserveOrderParam(
			userEmail,
			request.menuList(),
			request.address(),
			request.reservationTime(),
			request.storeRequest(),
			request.riderRequest(),
			request.storeId()
		);
	}
}