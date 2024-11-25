package com.example.foodDelivery.converter;

import com.example.foodDelivery.dao.UserEntity;
import com.example.foodDelivery.dto.UserDto;

public class UserConverter {
    public static UserEntity dtoToEntity(UserDto userDto, String role){
        return UserEntity.builder()
                .name(userDto.getName())
                .mail(userDto.getMail())
                .phoneNumber(userDto.getPhoneNumber())
                .userType(role)
                .build();
    }

    public static UserDto entityToDto(UserEntity userEntity){
        return UserDto.builder()
                .name(userEntity.getName())
                .mail(userEntity.getMail())
                .phoneNumber(userEntity.getPhoneNumber())
                .build();
    }
}