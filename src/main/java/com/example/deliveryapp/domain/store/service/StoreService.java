package com.example.deliveryapp.domain.store.service;

import com.example.deliveryapp.domain.store.dto.Param.CreateStoreParam;
import com.example.deliveryapp.domain.store.entity.Store;
import com.example.deliveryapp.domain.store.enumerate.StoreCategory;
import com.example.deliveryapp.domain.store.repository.StoreRepository;
import com.example.deliveryapp.global.exception.ApplicationException;
import com.example.deliveryapp.global.exception.ErrorCode;
import com.example.deliveryapp.global.exception.ErrorResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {
	private final StoreRepository storeRepository;

	@Transactional
	public void createStore(CreateStoreParam createStoreParam) {
		storeRepository.save(Store.builder().storeName(createStoreParam.storeName())
			.deliveryTip(createStoreParam.deliveryTip())
			.menuName(createStoreParam.menuName())
			.menuPrice(createStoreParam.menuPrice())
			.category(createStoreParam.category())
			.build());
	}

	@Transactional
	public void updateStore(Long storeId, String storeName, Long deliveryTip, Long menuPrice, String menuName,
		StoreCategory category) {
		Store store = getStore(storeId);
		store.updateData(storeName, deliveryTip, menuPrice, menuName,category);
	}

	@Transactional
	public void deleteStore(Long storeId) {
		storeRepository.delete(getStore(storeId));
	}

	public Store getStore(Long storeId) {
		return storeRepository.findById(storeId).orElseThrow(
		);
	}

	public Page<Store> getStoreList(Pageable pageable) {
		return storeRepository.findAll(pageable);

	}
}
