package com.example.deliveryapp.domain.user.entity;

import com.example.deliveryapp.domain.user.enumerate.UserRole;
import com.example.deliveryapp.global.common.BaseTimeEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nickname;
	private String name;
	private String email;
	private String phoneNumber;
	private String refreshToken;
	private UserRole role;

	@Builder
	public User(String nickname, String name, String email, String phoneNumber, String refreshToken, UserRole role){
		this.nickname = nickname;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.refreshToken =refreshToken;
		this.role = role;
	}

	public void updateRefreshToken(String refreshToken){
		this.refreshToken = refreshToken;
	}

}
