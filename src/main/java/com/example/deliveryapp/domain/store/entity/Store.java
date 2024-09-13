package com.example.deliveryapp.domain.store.entity;

import com.example.deliveryapp.domain.store.enumerate.StoreCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "store")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id", nullable = false)
    private Long id;

    private String store_name;

    private Float grade;

    private Long delivery_tip;
    private String menu_name;
    private Long menu_price;

    private StoreCategory category;
}
