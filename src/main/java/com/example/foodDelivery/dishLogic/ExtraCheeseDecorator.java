package com.example.foodDelivery.dishLogic;

public class ExtraCheeseDecorator extends DishDecorator {
    public ExtraCheeseDecorator(Dish dish) {
        super(dish);
    }
    @Override
    public String getDescription() {
        return dish.getDescription() + ", Extra Cheese";
    }
    @Override
    public double getCost() {
        return dish.getCost() + 2.0;
    }
}