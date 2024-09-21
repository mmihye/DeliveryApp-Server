package com.example.deliveryapp.global.exception;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ApplicationException.class)
	protected ResponseEntity<ErrorResponse> handleApplicationException(ApplicationException e){
		log.error(e + " " + e.getErrorCode().toString());
		return ResponseEntity.status(e.getErrorCode().getHttpStatus())
			.body(new ErrorResponse(e.getErrorCode()));
	}

	@ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
		log.error(e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(new ErrorResponse(e.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		// 에러 메시지 맵을 문자열로 변환
		String errorDetails = errors.entrySet().stream()
			.map(entry -> entry.getKey() + "=" + entry.getValue())
			.reduce((entry1, entry2) -> entry1 + ", " + entry2)
			.orElse("");

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(new ErrorResponse(errorDetails));
	}


}
