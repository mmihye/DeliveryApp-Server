package com.example.deliveryapp.domain.store.entity;

import com.example.deliveryapp.domain.store.enumerate.StoreCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Enumerated(EnumType.STRING)
    private StoreCategory category;

    @Builder
    public Store(String store_name, Long delivery_tip, String menu_name, Long menu_price, StoreCategory category){
        this.store_name = store_name;
        this.delivery_tip=delivery_tip;
        this.menu_name=menu_name;
        this.menu_price=menu_price;
        this.category = category;
        this.grade = 0F;
    }
}
