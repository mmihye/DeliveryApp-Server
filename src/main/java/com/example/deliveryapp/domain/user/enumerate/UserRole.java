package com.example.deliveryapp.domain.user.enumerate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
	GENERAL("ROLE_GENERAL", "일반"),
	ADMIN("ROLE_ADMIN", "관리자");

	private final String role;
	private final String title;
}
