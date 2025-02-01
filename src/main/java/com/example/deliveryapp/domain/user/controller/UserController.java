package com.example.deliveryapp.domain.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.deliveryapp.domain.user.dto.Param.SignInParam;
import com.example.deliveryapp.domain.user.dto.Request.SignInReq;
import com.example.deliveryapp.domain.user.dto.Response.LoginRes;
import com.example.deliveryapp.domain.user.entity.User;
import com.example.deliveryapp.domain.user.service.UserService;
import com.example.deliveryapp.global.common.ApiResponse;
import com.example.deliveryapp.global.exception.Success;
import com.example.deliveryapp.global.jwt.TokenProvider;
import com.example.deliveryapp.global.jwt.dto.TokenDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {
	private final UserService userService;
	private final TokenProvider tokenProvider;

	@PostMapping("/sign-in")
	public ApiResponse<LoginRes> signIn(
		@RequestBody SignInReq signInReq
	) {
		User user = userService.signIn(SignInParam.of(signInReq));
		TokenDto tokenDto = tokenProvider.createToken(user);
		return ApiResponse.success(Success.CREATE_SUCCESS,
			new LoginRes(user.getId(), user.getEmail(), user.getName(), user.getNickname(), user.getPhoneNumber(),
				tokenDto.accessToken(), tokenDto.refreshToken())
		);
	}

	@GetMapping("/login")
	public ApiResponse<LoginRes> login(
		@RequestBody SignInReq signInReq
	) {
		User user = userService.signIn(SignInParam.of(signInReq));
		TokenDto tokenDto = tokenProvider.createToken(user);
		return ApiResponse.success(Success.CREATE_SUCCESS,
			new LoginRes(user.getId(), user.getEmail(), user.getName(), user.getNickname(), user.getPhoneNumber(),
				tokenDto.accessToken(), tokenDto.refreshToken())
		);
	}

}
