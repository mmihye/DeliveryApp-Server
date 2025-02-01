package com.example.deliveryapp.domain.store.dto.Response;

import java.time.LocalDateTime;

import com.example.deliveryapp.domain.store.entity.Store;
import com.example.deliveryapp.domain.store.enumerate.StoreCategory;

public record StoreRes(
	Long storeId,

	String storeName,
	Float grade,
	Long deliveryTip,
	StoreCategory category,
	LocalDateTime time
) {
	public static StoreRes of(Store store) {
		return new StoreRes(store.getId(), store.getStoreName(), store.getGrade(), store.getDeliveryTip(),
			store.getCategory(), LocalDateTime.now());
	}
}
