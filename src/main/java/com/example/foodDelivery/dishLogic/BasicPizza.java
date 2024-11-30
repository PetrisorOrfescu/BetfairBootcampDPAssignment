package com.example.foodDelivery.dishLogic;

public class BasicPizza implements Dish {


    @Override
    public String getDescription() {
        return "Basic Pizza";
    }

    @Override
    public double getCost() {
        return 8.0;
    }
}
