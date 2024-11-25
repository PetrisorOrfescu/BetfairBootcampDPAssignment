package com.example.foodDelivery.repository;

import com.example.foodDelivery.dao.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {}
