package com.example.deliveryapp.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum Success {

	/**
	 * 201 CREATED
	 */
	CREATE_SUCCESS(HttpStatus.CREATED, "생성 성공"),

	/**
	 * 200 OK
	 */
	SUCCESS(HttpStatus.OK, "성공");

	/**
	 * 204 NO_CONTENT
	 */

	private final HttpStatus httpStatus;
	private final String message;

	public int getHttpStatusCode() {
		return httpStatus.value();
	}
}
