package com.example.foodDelivery.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DishDto {
    private Long id;
    private String description;
    private Double cost;
}
