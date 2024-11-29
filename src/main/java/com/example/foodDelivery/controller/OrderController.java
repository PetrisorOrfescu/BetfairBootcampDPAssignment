package com.example.foodDelivery.controller;

import com.example.foodDelivery.constants.OrderConstants;
import com.example.foodDelivery.dto.OrderDto;
import com.example.foodDelivery.dto.ResponseDto;
import com.example.foodDelivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createOrder(@RequestBody OrderDto orderDto){
        orderService.createOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(OrderConstants.STATUS_201, OrderConstants.MESSAGE_201));
    }
}
