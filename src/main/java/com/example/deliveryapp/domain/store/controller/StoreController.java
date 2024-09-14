package com.example.deliveryapp.domain.store.controller;

import com.example.deliveryapp.domain.store.dto.CreateStoreReq;
import com.example.deliveryapp.domain.store.enumerate.StoreCategory;
import com.example.deliveryapp.domain.store.service.StoreService;
import com.example.deliveryapp.global.common.ApiResponse;
import com.example.deliveryapp.global.exception.Success;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/store")
public class StoreController {

    private final StoreService storeService;

    @PostMapping("")
    public ApiResponse<?> createStore(
            @RequestBody CreateStoreReq createStoreReq
    ){
        storeService.createStore(createStoreReq);
        return ApiResponse.success(Success.CREATE_STORE_SUCCESS);
    }

    @PatchMapping("/{storeId}")
    public ApiResponse<?> updateStore(
            @PathVariable Long storeId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Long deliveryTip,
            @RequestParam(required = false) Long menuPrice,
            @RequestParam(required = false) String menuName,
            @RequestParam(required = false) StoreCategory category
    ){
        storeService.updateStore(storeId,storeName,deliveryTip,menuPrice,menuName,category);
        return ApiResponse.success(Success.UPDATE_STORE_SUCCESS);
    }

    @DeleteMapping("/{storeId}")
    public ApiResponse<?> deleteStore(
            @PathVariable Long storeId
    ){
        storeService.deleteStore(storeId);
        return ApiResponse.success(Success.DELETE_STORE_SUCCESS);
    }
}
