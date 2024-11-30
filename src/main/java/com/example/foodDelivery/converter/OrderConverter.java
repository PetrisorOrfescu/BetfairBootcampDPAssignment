package com.example.foodDelivery.converter;

import com.example.foodDelivery.dao.OrderEntity;
import com.example.foodDelivery.dto.OrderDto;

import java.util.List;

public class OrderConverter {

    public static OrderEntity dtoToEntity(OrderDto dto) {
        return OrderEntity.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .orderStatus(dto.getOrderStatus())
                .dishes(DishConverter.dtoListToEntityList(dto.getDishes()))
                .build();
    }

    public static OrderDto entityToDto(OrderEntity order) {
        return OrderDto.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .orderStatus(order.getOrderStatus())
                .dishes(DishConverter.entityListToDtoList(order.getDishes()))
                .build();
    }

    public static List<OrderDto> entityListToDtoList(List<OrderEntity> orderEntities) {
        return orderEntities.stream()
                .map(OrderConverter::entityToDto)
                .toList();
    }

    public static List<OrderEntity> dtoListToEntityList(List<OrderDto> orderDtos) {
        return orderDtos.stream()
                .map(OrderConverter::dtoToEntity)
                .toList();
    }
}
