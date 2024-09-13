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
    CREATE_STORE_SUCCESS(HttpStatus.CREATED, "가게 생성 성공");


    /**
     * 200 OK
     */


    /**
     * 204 NO_CONTENT
     */


    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode(){
        return httpStatus.value();
    }
}
