package com.example.deliveryapp.domain.store.dto.Param;

import com.example.deliveryapp.domain.store.dto.Request.CreateStoreReq;
import com.example.deliveryapp.domain.store.enumerate.StoreCategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateStoreParam(
	String storeName,
	Long deliveryTip,
	StoreCategory category
) {
	public static CreateStoreParam of(CreateStoreReq createStoreReq) {
		return new CreateStoreParam(createStoreReq.storeName(), createStoreReq.deliveryTip(),createStoreReq.category());
	}

}