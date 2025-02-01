package com.example.deliveryapp.global.exception;

import lombok.Getter;
import lombok.ToString;

import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ErrorCode {

	// 2000: Common Error
	INTERNAL_SERVER_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, 5000, "예기치 못한 오류가 발생했습니다."),

	USER_ALREADY_EXISTS(HttpStatus.INTERNAL_SERVER_ERROR, 5001, "이미 가입된 사용자입니다."),

	// 3000: Auth Error
	WRONG_TOKEN_EXCEPTION(HttpStatus.UNAUTHORIZED, 3002, "유효하지 않은 토큰입니다."),

	VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, 2000, "유효하지 않은 요청값입니다."),
	FORBIDDEN_EXCEPTION(HttpStatus.FORBIDDEN, 2005, "인가되지 않는 요청입니다."),

	NOT_FOUND_EXCEPTION(HttpStatus.BAD_REQUEST, 4000, "요청된 값이 존재하지 않습니다.");


	//5000: Post Error

	private final HttpStatus httpStatus;
	private final Integer code;
	private final String message;

	ErrorCode(HttpStatus httpStatus, Integer code, String message) {
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
	}
}
