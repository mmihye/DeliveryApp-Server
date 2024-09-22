package com.example.deliveryapp.domain.store.entity;

import com.example.deliveryapp.domain.menu.entity.Menu;
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

	@Enumerated(EnumType.STRING)
	private StoreCategory category;

	@Builder
	public Store(String storeName, Long deliveryTip, StoreCategory category) {
		this.storeName = storeName;
		this.deliveryTip = deliveryTip;
		this.category = category;
		this.grade = 0F;
	}

	public void updateData(String storeName, Long deliveryTip, StoreCategory category) {
		if (storeName != null)
			this.storeName = storeName;
		if (deliveryTip != null)
			this.deliveryTip = deliveryTip;
		if (category != null)
			this.category = category;
	}

}
