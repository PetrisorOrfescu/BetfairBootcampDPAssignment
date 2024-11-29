package com.example.foodDelivery.dishLogic;

public class BasicPizza implements Dish{
    private final String description = "Basic Pizza";
    private final double cost = 8.0;


    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getCost() {
        return cost;
    }
}
