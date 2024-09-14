package com.example.deliveryapp.domain.store.dto;

import com.example.deliveryapp.domain.store.enumerate.StoreCategory;

public record CreateStoreReq(
        String storeName,
        Long deliveryTip,
        String menuName,
        Long menuPrice,
        StoreCategory category
) {

}
