package com.example.deliveryapp.domain.user.module;

import org.springframework.stereotype.Component;

import com.example.deliveryapp.domain.user.entity.User;
import com.example.deliveryapp.domain.user.repository.UserRepository;
import com.example.deliveryapp.global.exception.ApplicationException;
import com.example.deliveryapp.global.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserModule {
	private final UserRepository userRepository;

	public User getUser(Long userId) {
		return userRepository.findById(userId).orElseThrow(
			() -> new ApplicationException(ErrorCode.NOT_FOUND_USER_EXCEPTION)
		);
	}

	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email).orElseThrow(
			() -> new ApplicationException(ErrorCode.NOT_FOUND_USER_EXCEPTION)
		);
	}

}
