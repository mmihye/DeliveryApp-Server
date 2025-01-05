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

	public static UserRole fromString(String roleName) {
		for (UserRole userRole : UserRole.values()) {
			if (userRole.name().equalsIgnoreCase(roleName)) {
				return userRole;
			}
		}
		throw new IllegalArgumentException("Unknown role: " + roleName);
	}
}
