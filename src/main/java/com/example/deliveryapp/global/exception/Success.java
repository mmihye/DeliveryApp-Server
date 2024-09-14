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
    CREATE_STORE_SUCCESS(HttpStatus.CREATED, "가게 생성 성공"),


    /**
     * 200 OK
     */
    UPDATE_STORE_SUCCESS(HttpStatus.OK, "가게 정보 수정 성공"),
    DELETE_STORE_SUCCESS(HttpStatus.OK, "가게 삭제 성공"),
    GET_STORE_SUCCESS(HttpStatus.OK,"가게 조회 성공");


    /**
     * 204 NO_CONTENT
     */


    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode(){
        return httpStatus.value();
    }
}
