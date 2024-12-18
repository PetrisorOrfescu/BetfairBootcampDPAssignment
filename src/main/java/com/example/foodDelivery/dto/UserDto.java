package com.example.foodDelivery.dto;

import com.example.foodDelivery.dao.UserType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String name;

    private String mail;

    private String phoneNumber;

    private UserType userType;
}
