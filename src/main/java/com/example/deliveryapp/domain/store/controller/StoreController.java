package com.example.deliveryapp.domain.store.controller;

import java.util.stream.Collectors;

import com.example.deliveryapp.domain.store.dto.PageData;
import com.example.deliveryapp.domain.store.dto.Param.CreateStoreParam;
import com.example.deliveryapp.domain.store.dto.Request.CreateStoreReq;
import com.example.deliveryapp.domain.store.dto.Response.StoreListRes;
import com.example.deliveryapp.domain.store.dto.Response.StoreRes;
import com.example.deliveryapp.domain.store.entity.Store;
import com.example.deliveryapp.domain.store.enumerate.StoreCategory;
import com.example.deliveryapp.domain.store.service.StoreService;
import com.example.deliveryapp.global.common.ApiResponse;
import com.example.deliveryapp.global.exception.Success;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/stores")
public class StoreController {

	private final StoreService storeService;

	@GetMapping("/{storeId}")
	public ApiResponse<StoreRes> getStore(
		@PathVariable Long storeId
	) {
		StoreRes response = StoreRes.of(storeService.getStore(storeId));
		return ApiResponse.success(Success.SUCCESS, response);
	}

	@GetMapping("")
	public ApiResponse<StoreListRes> getStoreList(
		@PageableDefault(size = 10, page = 0) Pageable pageable
	) {
		Page<Store> storePage = storeService.getStoreList(pageable);
		StoreListRes response = new StoreListRes(storePage.getContent().stream()
			.map(StoreRes::of).collect(Collectors.toList()), PageData.of(storePage));

		return ApiResponse.success(Success.SUCCESS, response);
	}

	@PostMapping("")
	public ApiResponse<?> createStore(
		@RequestBody @Valid CreateStoreReq createStoreReq
	) {
		storeService.createStore(CreateStoreParam.of(createStoreReq));
		return ApiResponse.success(Success.CREATE_SUCCESS);
	}

	@PatchMapping("/{storeId}")
	public ApiResponse<?> updateStore(
		@PathVariable Long storeId,
		@RequestParam(required = false) String storeName,
		@RequestParam(required = false) Long deliveryTip,
		@RequestParam(required = false) StoreCategory category
	) {
		storeService.updateStore(storeId, storeName, deliveryTip, category);
		return ApiResponse.success(Success.SUCCESS);
	}

	@DeleteMapping("/{storeId}")
	public ApiResponse<?> deleteStore(
		@PathVariable Long storeId
	) {
		storeService.deleteStore(storeId);
		return ApiResponse.success(Success.SUCCESS);
	}
}
