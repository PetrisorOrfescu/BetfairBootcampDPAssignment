package com.example.foodDelivery.repository;

import com.example.foodDelivery.dao.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<DishEntity, Long> {
}
