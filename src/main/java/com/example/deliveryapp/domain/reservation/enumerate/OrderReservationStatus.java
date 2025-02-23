package com.example.deliveryapp.domain.reservation.enumerate;

public enum OrderReservationStatus {
	PENDING,      // 예약 대기
	CANCELED,     // 예약 취소
	PROCESSING,   // 처리 중
	COMPLETED,    // 주문 완료
	FAILED       // 주문 실패
}
