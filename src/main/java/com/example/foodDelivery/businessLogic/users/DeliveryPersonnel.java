package com.example.foodDelivery.businessLogic.users;

public class DeliveryPersonnel implements User{
    @Override
    public String getPermissions() {
        return "Delivery person, delivers orders";
    }
}
