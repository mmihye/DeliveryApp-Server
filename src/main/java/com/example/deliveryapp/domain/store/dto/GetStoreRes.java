package com.example.deliveryapp.domain.store.dto;

import com.example.deliveryapp.domain.store.entity.Store;
import com.example.deliveryapp.domain.store.enumerate.StoreCategory;

public record GetStoreRes(
        Long storeId,

        String storeName,
        Float grade,
        Long deliveryTip,
        String menuName,
        Long menuPrice,
        StoreCategory category
) {
    public static GetStoreRes of(Store store){
        return new GetStoreRes(store.getId(), store.getStoreName(), store.getGrade(), store.getDeliveryTip(), store.getMenuName(), store.getMenuPrice(), store.getCategory());
    }
}
