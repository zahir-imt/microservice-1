package com.bookstore.order.domain;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String message) {
        super(message);
    }

    public static OrderNotFoundException forCode(String number) {
        return new OrderNotFoundException("Order with number " + number + "not found");
    }
}
