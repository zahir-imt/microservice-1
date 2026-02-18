package com.bookstore.notification;

import org.springframework.boot.context.properties.ConfigurationProperties;

// Application properties
@ConfigurationProperties(prefix = "notifications")
public record ApplicationProperties(
        String orderEventsExchange,
        String newOrdersQueue,
        String deliveredOrdersQueue,
        String cancelledOrdersQueue,
        String errorOrdersQueue,
        String supportEmail) {}
