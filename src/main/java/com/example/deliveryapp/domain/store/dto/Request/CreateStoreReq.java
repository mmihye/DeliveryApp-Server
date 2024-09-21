package com.example.deliveryapp.domain.store.dto.Request;

import com.example.deliveryapp.domain.store.enumerate.StoreCategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateStoreReq(
	@NotBlank(message = "가게이름은 필수 입력 값입니다.")
	String storeName,
	@NotNull(message = "배달팁은 필수 입력 값입니다.")
	@PositiveOrZero
	Long deliveryTip,
	@NotBlank(message = "메뉴이름은 필수 입력 값입니다.")
	String menuName,
	@NotNull(message = "메뉴가격은 필수 입력 값입니다.")
	@PositiveOrZero
	Long menuPrice,
	@NotNull(message = "카테고리는 필수 입력 값입니다.")
	StoreCategory category
) {

}
