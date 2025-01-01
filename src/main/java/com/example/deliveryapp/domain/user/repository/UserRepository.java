package com.example.deliveryapp.domain.user.repository;

import java.util.Optional;
import java.util.OptionalLong;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.deliveryapp.domain.store.entity.Store;
import com.example.deliveryapp.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);
}
