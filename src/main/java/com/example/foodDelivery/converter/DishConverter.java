package com.example.foodDelivery.converter;

import com.example.foodDelivery.dao.DishEntity;
import com.example.foodDelivery.dto.DishDto;

import java.util.List;

public class DishConverter {
    public static DishEntity dtoToEntity(DishDto dto) {
        return DishEntity.builder()
                .id(dto.getId())
                .cost(dto.getCost())
                .description(dto.getDescription())
                .build();
    }

    public static DishDto entityToDto(DishEntity dish) {
        return DishDto.builder()
                .id(dish.getId())
                .cost(dish.getCost())
                .description(dish.getDescription())
                .build();
    }

    public static List<DishDto> entityListToDtoList(List<DishEntity> dishEntities) {
        return dishEntities.stream()
                .map(DishConverter::entityToDto)
                .toList();
    }

    public static List<DishEntity> dtoListToEntityList(List<DishDto> dishDtos) {
        return dishDtos.stream()
                .map(DishConverter::dtoToEntity)
                .toList();
    }

}
