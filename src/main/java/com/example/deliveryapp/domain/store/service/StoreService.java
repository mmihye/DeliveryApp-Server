package com.example.deliveryapp.domain.store.service;

import com.example.deliveryapp.domain.store.dto.CreateStoreReq;
import com.example.deliveryapp.domain.store.entity.Store;
import com.example.deliveryapp.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;


    @Transactional
    public void createStore(CreateStoreReq createStoreReq) {
        storeRepository.save(Store.builder().store_name(createStoreReq.store_name())
                .delivery_tip(createStoreReq.delivery_tip())
                .menu_name(createStoreReq.menu_name())
                .menu_price(createStoreReq.menu_price())
                .category(createStoreReq.category())
                .build());
    }
}
