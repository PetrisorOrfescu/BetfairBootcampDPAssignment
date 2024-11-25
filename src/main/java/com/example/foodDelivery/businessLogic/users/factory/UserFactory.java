package com.example.foodDelivery.businessLogic.users.factory;

import com.example.foodDelivery.businessLogic.users.Admin;
import com.example.foodDelivery.businessLogic.users.Customer;
import com.example.foodDelivery.businessLogic.users.DeliveryPersonnel;
import com.example.foodDelivery.businessLogic.users.User;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    public static User createUser(String typeOfUser){
        return switch (typeOfUser.toLowerCase()) {
            case "admin" -> new Admin();
            case "customer" -> new Customer();
            case "delivery" -> new DeliveryPersonnel();
            default -> throw new IllegalArgumentException("Invalid user type");
        };
    }
}
