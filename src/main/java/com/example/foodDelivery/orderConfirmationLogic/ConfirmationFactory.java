package com.example.foodDelivery.orderConfirmationLogic;

public class ConfirmationFactory {
    public static Confirmation createConfirmation(String type) {
        switch (type.toLowerCase()) {
            case "email" -> {
                return new EmailConfirmation();
            }
            case "sms" -> {
                return new SMSConfirmation();
            }
            default -> throw new IllegalArgumentException("Unknown confirmation method type: " + type);
        }
    }
}
