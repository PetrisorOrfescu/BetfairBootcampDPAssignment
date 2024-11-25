package com.example.foodDelivery.businessLogic.users;

public class Customer implements User{

    @Override
    public String getPermissions() {
        return "Customer, browses menus, places orders";
    }
}
