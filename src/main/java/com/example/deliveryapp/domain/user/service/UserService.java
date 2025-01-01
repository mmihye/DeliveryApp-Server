package com.example.deliveryapp.domain.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.deliveryapp.domain.user.dto.Param.SignInParam;
import com.example.deliveryapp.domain.user.dto.Response.LoginRes;
import com.example.deliveryapp.domain.user.entity.User;
import com.example.deliveryapp.domain.user.repository.UserRepository;
import com.example.deliveryapp.global.exception.ApplicationException;
import com.example.deliveryapp.global.exception.ErrorCode;
import com.example.deliveryapp.global.jwt.TokenProvider;
import com.example.deliveryapp.global.jwt.dto.TokenDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final TokenProvider tokenProvider;

	@Transactional
	public User signIn(SignInParam signInParam) {
		if (userRepository.findByEmail(signInParam.email()).isPresent()) {
			throw new ApplicationException(ErrorCode.USER_ALREADY_EXISTS);
		}

		User newUser = User.builder()
			.email(signInParam.email())
			.name(signInParam.name())
			.phoneNumber(signInParam.phoneNumber())
			.nickname(signInParam.nickName())
			.build();


		userRepository.save(newUser);

		return newUser;

	}

}
