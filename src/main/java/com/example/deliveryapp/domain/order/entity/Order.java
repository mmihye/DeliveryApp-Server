package com.example.deliveryapp.domain.order.entity;

import java.util.List;

import com.example.deliveryapp.domain.menu.entity.Menu;
import com.example.deliveryapp.domain.store.entity.Store;
import com.example.deliveryapp.domain.store.enumerate.StoreCategory;
import com.example.deliveryapp.domain.user.entity.User;
import com.example.deliveryapp.global.common.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "order_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String address;
	private String storeRequest;
	private String riderRequest;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;

	@Builder
	public Order(String address, String storeRequest, String riderRequest, User user, Store store) {
		this.address = address;
		this.storeRequest = storeRequest;
		this.riderRequest = riderRequest;
		this.user = user;
		this.store = store;
	}
}
