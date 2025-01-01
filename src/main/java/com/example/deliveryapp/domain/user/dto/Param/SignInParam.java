package com.example.deliveryapp.domain.user.dto.Param;

import com.example.deliveryapp.domain.store.dto.Param.CreateStoreParam;
import com.example.deliveryapp.domain.store.dto.Request.CreateStoreReq;
import com.example.deliveryapp.domain.user.dto.Request.SignInReq;

public record SignInParam(
	String nickName,
	String name,
	String email,
	String phoneNumber
) {
	public static SignInParam of(SignInReq req) {
		return new SignInParam(req.nickName(), req.name(),req.email(), req.phoneNumber());
	}
}
