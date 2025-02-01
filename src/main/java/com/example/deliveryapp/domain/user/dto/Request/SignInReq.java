package com.example.deliveryapp.domain.user.dto.Request;

public record SignInReq(
		String nickname,
		String name,
		String email,
		String phoneNumber

) {
}
