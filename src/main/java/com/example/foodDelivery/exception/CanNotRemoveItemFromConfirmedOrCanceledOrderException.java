package com.example.foodDelivery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CanNotRemoveItemFromConfirmedOrCanceledOrderException extends Exception {
    public CanNotRemoveItemFromConfirmedOrCanceledOrderException(String message) {
        super(message);
    }
}
