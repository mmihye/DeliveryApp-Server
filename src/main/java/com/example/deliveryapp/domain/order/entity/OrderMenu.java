package com.example.deliveryapp.domain.order.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.deliveryapp.domain.menu.entity.Menu;
import com.example.deliveryapp.domain.store.entity.Store;
import com.example.deliveryapp.domain.user.entity.User;
import com.example.deliveryapp.global.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderMenu extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_menu_id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "order_table_id")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "menu")
	private Menu menu;
	@Builder
	public OrderMenu(Order order, Menu menu) {
		this.order = order;
		this.menu = menu;
	}
}
