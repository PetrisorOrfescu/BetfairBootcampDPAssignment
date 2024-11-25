package com.example.foodDelivery.controller;

import com.example.foodDelivery.constants.UserConstants;
import com.example.foodDelivery.dto.ResponseDto;
import com.example.foodDelivery.dto.UserDto;
import com.example.foodDelivery.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String hello (){
        return "Hi from the User endpoint!";
    }

    @PostMapping("/create/{role}")
    public ResponseEntity<ResponseDto> createUser(@PathVariable String role, @RequestBody UserDto userDto){
        userService.createUser(role, userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(UserConstants.STATUS_201, UserConstants.MESSAGE_201) );
    }
}
