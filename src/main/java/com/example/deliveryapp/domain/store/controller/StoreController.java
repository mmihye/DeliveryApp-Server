package com.example.deliveryapp.domain.store.controller;

import com.example.deliveryapp.domain.store.dto.CreateStoreReq;
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
}
