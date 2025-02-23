package com.example.deliveryapp.domain.menu.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.deliveryapp.domain.menu.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {

	List<Menu> findByIdIn(List<Long> menuIds);
}
