package com.example.deliveryapp.domain.reservation.dto.Request;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ReserveOrderReq(
	@NotEmpty(message = "메뉴를 한개 이상 주문해주세요.")
	List<Long> menuIds,
	@NotBlank(message = "주소는 필수 입력 값입니다.")
	String address,
	String storeRequest,
	String riderRequest,
	@NotNull(message = "예약시간은 필수 입력 값입니다.")
	LocalDateTime reservationTime,
	@NotNull(message = "가게는 필수 입력 값입니다.")
	Long storeId
){

}
