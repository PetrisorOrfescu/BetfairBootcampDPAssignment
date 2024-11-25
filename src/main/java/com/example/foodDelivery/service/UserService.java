package com.example.foodDelivery.service;

import com.example.foodDelivery.businessLogic.users.Admin;
import com.example.foodDelivery.businessLogic.users.Customer;
import com.example.foodDelivery.businessLogic.users.DeliveryPersonnel;
import com.example.foodDelivery.businessLogic.users.User;
import com.example.foodDelivery.businessLogic.users.factory.UserFactory;
import com.example.foodDelivery.converter.UserConverter;
import com.example.foodDelivery.dao.UserEntity;
import com.example.foodDelivery.dto.UserDto;
import com.example.foodDelivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void createUser(String role, UserDto userDto) {
        User user = UserFactory.createUser(role);
        UserEntity userEntity  = new UserEntity();
        switch (user) {
            case Admin ignored -> userEntity = UserConverter.dtoToEntity(userDto, "Administrator");
            case Customer ignored -> userEntity = UserConverter.dtoToEntity(userDto, "Customer");
            case DeliveryPersonnel ignored ->
                    userEntity = UserConverter.dtoToEntity(userDto, "Delivery Personnel");
            default -> {}
        }

        userRepository.save(userEntity);

    }
//    saveAdmin
//    saveCustomer
//    saveDeliveryPersonnel
    //save (User) -> switch de instanceOf si de acolo mai vedem

}
