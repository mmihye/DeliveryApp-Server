package com.example.deliveryapp.domain.store.dto.Response;

import java.util.List;

import com.example.deliveryapp.domain.store.dto.PageData;
import com.example.deliveryapp.domain.store.dto.Response.StoreRes;

public record StoreListRes(
	List<StoreRes> storeResList,
	PageData pageData
) {
}
