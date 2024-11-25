package com.example.foodDelivery.businessLogic.users;

public class Admin implements User{

    @Override
    public String getPermissions() {
        return "Administrator, manages users, updates menus";
    }
}
