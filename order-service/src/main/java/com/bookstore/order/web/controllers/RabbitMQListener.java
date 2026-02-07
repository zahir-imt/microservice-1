package com.bookstore.order.web.controllers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListener {

    @RabbitListener(queues = "${orders.new-orders-queue}")
    public void handleNewOrder(RabbitMQDemoController.MyPayload payload) {
        System.out.println("New Order: " + payload.content() + " " + payload.name());
    }

    @RabbitListener(queues = "${orders.delivered-orders-queue}")
    public void deliveredOrder(RabbitMQDemoController.MyPayload payload) {
        System.out.println("Delivered order :" + payload.content() + " " + payload.name());
    }
}
