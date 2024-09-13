package com.example.deliveryapp.domain.store.dto;

import com.example.deliveryapp.domain.store.enumerate.StoreCategory;

public record CreateStoreReq(
        String store_name,
        Long delivery_tip,
        String menu_name,
        Long menu_price,
        StoreCategory category
) {

}
