package com.example.deliveryapp.domain.user.dto.Request;

public record SignInReq(
		String nickName,
		String name,
		String email,
		String phoneNumber

) {
}
