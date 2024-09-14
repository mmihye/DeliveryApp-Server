package com.example.deliveryapp.domain.store.service;

import com.example.deliveryapp.domain.store.dto.CreateStoreReq;
import com.example.deliveryapp.domain.store.dto.StoreListRes;
import com.example.deliveryapp.domain.store.dto.StoreRes;
import com.example.deliveryapp.domain.store.entity.Store;
import com.example.deliveryapp.domain.store.enumerate.StoreCategory;
import com.example.deliveryapp.domain.store.repository.StoreRepository;
import com.example.deliveryapp.global.exception.ApplicationException;
import com.example.deliveryapp.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;


    @Transactional
    public void createStore(CreateStoreReq createStoreReq) {
        storeRepository.save(Store.builder().storeName(createStoreReq.storeName())
                .deliveryTip(createStoreReq.deliveryTip())
                .menuName(createStoreReq.menuName())
                .menuPrice(createStoreReq.menuPrice())
                .category(createStoreReq.category())
                .build());
    }

    @Transactional
    public void updateStore(Long storeId, String storeName, Long deliveryTip, Long menuPrice, String menuName, StoreCategory category) {
        Store store = getStoreFromRepo(storeId);

        if(storeName != null)
            store.setStoreName(storeName);
        if(deliveryTip != null)
            store.setDeliveryTip(deliveryTip);
        if(menuName != null)
            store.setMenuName(menuName);
        if(menuPrice != null)
            store.setMenuPrice(menuPrice);
        if(category != null)
            store.setCategory(category);
    }
    @Transactional
    public void deleteStore(Long storeId) {
        storeRepository.delete(getStoreFromRepo(storeId));
    }


    public StoreRes getStore(Long storeId) {
        return StoreRes.of(getStoreFromRepo(storeId));
    }


    private Store getStoreFromRepo(Long storeId){
        return storeRepository.findById(storeId).orElseThrow(
                () -> new ApplicationException(ErrorCode.NOT_FOUND_STORE_EXCEPTION)
        );
    }

    public StoreListRes getStoreList(Pageable pageable) {
        Page<Store> storePage = storeRepository.findAll(pageable);

        return new StoreListRes(storePage.getContent().stream()
                .map(StoreRes::of)
                .collect(Collectors.toList())
                ,storePage.getNumber(), storePage.getSize(), storePage.getTotalPages());

    }
}
