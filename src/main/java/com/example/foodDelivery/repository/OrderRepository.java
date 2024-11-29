package com.example.foodDelivery.repository;

import com.example.foodDelivery.dao.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<OrderEntity,Long > {

}
