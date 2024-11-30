package com.example.foodDelivery.dto;

import com.example.foodDelivery.dao.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderDto {
    private Long id;
    private Long userId;
    private OrderStatus orderStatus;
    private List<DishDto> dishes;
}
