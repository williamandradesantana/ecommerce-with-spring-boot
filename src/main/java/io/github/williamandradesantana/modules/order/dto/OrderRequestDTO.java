package io.github.williamandradesantana.modules.order.dto;

import io.github.williamandradesantana.modules.order.entity.Order;
import io.github.williamandradesantana.modules.order.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;

public record OrderRequestDTO(
        Instant orderDate,
        OrderStatus orderStatus,
        BigDecimal total
) {
    public OrderRequestDTO(Order order) {
        this(order.getOrderDate(), order.getOrderStatus(), order.getTotal());
    }
}
