package com.example.deliveryapp.domain.store.dto;

import java.util.List;

public record StoreListRes(
        List<StoreRes> storeResList,

        Integer pageNo,
        Integer pageSize,
        Integer totalSize
) {
}
