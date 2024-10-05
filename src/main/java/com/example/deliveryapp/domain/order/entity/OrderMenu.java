package com.example.deliveryapp.domain.order.entity;

import com.example.deliveryapp.domain.menu.entity.Menu;
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

	@OneToOne
	@JoinColumn(name = "menu")
	private Menu menu;

}
