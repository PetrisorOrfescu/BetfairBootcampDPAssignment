package com.example.foodDelivery.converter;

import com.example.foodDelivery.dao.DishEntity;
import com.example.foodDelivery.dto.DishDto;

public class DishConverter {
    public static DishEntity dtoToEntity(DishDto dto){
        return DishEntity.builder()
                .cost(dto.getCost())
                .description(dto.getDescription())
                .build();
    }
    public static DishDto dtoToEntity(DishEntity dish){
        return DishDto.builder()
                .cost(dish.getCost())
                .description(dish.getDescription())
                .build();
    }
}
