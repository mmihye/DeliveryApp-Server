package com.example.deliveryapp.domain.store.entity;

import com.example.deliveryapp.domain.store.enumerate.StoreCategory;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "store")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "store_id", nullable = false)
	private Long id;

	private String storeName;

	private Float grade;

	private Long deliveryTip;
	private String menuName;
	private Long menuPrice;

	@Enumerated(EnumType.STRING)
	private StoreCategory category;

	@Builder
	public Store(String storeName, Long deliveryTip, String menuName, Long menuPrice, StoreCategory category) {
		this.storeName = storeName;
		this.deliveryTip = deliveryTip;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.category = category;
		this.grade = 0F;
	}

	public void updateData(String storeName, Long deliveryTip, Long menuPrice, String menuName, StoreCategory category) {
		if (storeName != null)
			this.storeName = storeName;
		if (deliveryTip != null)
			this.deliveryTip = deliveryTip;
		if (menuName != null)
			this.menuName = menuName;
		if (menuPrice != null)
			this.menuPrice = menuPrice;
		if (category != null)
			this.category = category;
	}

}
