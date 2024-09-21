package com.example.deliveryapp.domain.store.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public record PageData(
	Integer pageNo,
	Integer pageSize,
	Integer totalSize
) {
	public static PageData of(Page<?> page) {
		return new PageData(page.getNumber(), page.getSize(), page.getTotalPages());
	}
}
