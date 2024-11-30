package com.example.foodDelivery.repository;

import com.example.foodDelivery.dao.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    Optional<List<OrderEntity>> findAllByUserId(Long userId);
}
