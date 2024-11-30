package com.example.foodDelivery.orderConfirmationLogic;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SMSConfirmation implements Confirmation {
    @Override
    public void send(String message) {
        log.debug("Sending to EMail: {}", message);
    }
}

