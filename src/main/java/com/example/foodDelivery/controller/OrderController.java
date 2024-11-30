package com.example.foodDelivery.controller;

import com.example.foodDelivery.constants.OrderConstants;
import com.example.foodDelivery.dto.OrderDto;
import com.example.foodDelivery.dto.ResponseDto;
import com.example.foodDelivery.exception.CanNotRemoveItemFromConfirmedOrCanceledOrderException;
import com.example.foodDelivery.exception.ResourceNotFoundException;
import com.example.foodDelivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createOrder(@RequestParam String customerPhoneNumber) throws ResourceNotFoundException {
        orderService.createOrder(customerPhoneNumber);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(OrderConstants.STATUS_201, OrderConstants.MESSAGE_201));
    }

    @GetMapping("/all")
    public List<OrderDto> getOrdersForUserPhoneNumber(@RequestParam String customerPhoneNumber) throws ResourceNotFoundException {
        return orderService.getOrderByPhoneNumber(customerPhoneNumber);
    }

    @PutMapping("/confirm")
    public ResponseEntity<ResponseDto> confirmOrder(@RequestParam Long orderId, @RequestParam String confirmationMethod) throws ResourceNotFoundException {
        boolean isConfirmed = orderService.confirmOrder(orderId, confirmationMethod);
        if (isConfirmed) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(OrderConstants.STATUS_200, OrderConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(OrderConstants.STATUS_417, OrderConstants.MESSAGE_417_UPDATE));
        }
    }

    @PutMapping("/cancel")
    public ResponseEntity<ResponseDto> cancelOrder(@RequestParam Long orderId) throws ResourceNotFoundException {
        boolean isCanceled = orderService.cancelOrder(orderId);
        if (isCanceled) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(OrderConstants.STATUS_200, OrderConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(OrderConstants.STATUS_417, OrderConstants.MESSAGE_417_CANCEL));
        }
    }

    @PutMapping("/addPizza")
    public ResponseEntity<ResponseDto> addPizzaToOrder(@RequestParam Long orderId, @RequestParam Boolean extraCheese) throws ResourceNotFoundException {

        boolean addedToOrder = orderService.addPizzaToOrder(orderId, extraCheese);
        if (addedToOrder) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(OrderConstants.STATUS_200, OrderConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(OrderConstants.STATUS_417, OrderConstants.MESSAGE_417_ADD_TO_ORDER));
        }

    }

    @PutMapping("/removeFromOrder")
    public ResponseEntity<ResponseDto> removeItemFromOrder(@RequestParam Long orderId, @RequestParam Long itemId) throws ResourceNotFoundException, CanNotRemoveItemFromConfirmedOrCanceledOrderException {

        boolean addedToOrder = orderService.removeFromOrder(orderId, itemId);
        if (addedToOrder) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(OrderConstants.STATUS_200, OrderConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(OrderConstants.STATUS_417, OrderConstants.MESSAGE_417_REMOVE_FROM_ORDER));
        }

    }
}
