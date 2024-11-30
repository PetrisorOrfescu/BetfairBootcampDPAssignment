package com.example.foodDelivery.constants;

public class OrderConstants {
    private OrderConstants() {
    }

    public static final String STATUS_201 = "201";
    public static final String MESSAGE_201 = "Order created successfully";
    public static final String STATUS_200 = "200";
    public static final String MESSAGE_200 = "Request processed successfully";
    public static final String STATUS_417 = "417";
    public static final String MESSAGE_417_UPDATE = "Update operation failed. Please try again or contact Dev team";
    public static final String MESSAGE_417_CANCEL = "Cancel operation failed. Please try again or contact Dev team";
    public static final String MESSAGE_417_ADD_TO_ORDER = "Item could not be added to order. Please try again or contact Dev team";
    public static final String MESSAGE_417_REMOVE_FROM_ORDER = "Item could not be removed from order. Please try again or contact Dev team";
}
