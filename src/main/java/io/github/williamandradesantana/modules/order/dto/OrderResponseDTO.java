package io.github.williamandradesantana.modules.order.dto;

import io.github.williamandradesantana.modules.order.entity.Order;
import io.github.williamandradesantana.modules.order.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record OrderResponseDTO(
        UUID id,
        Instant orderDate,
        OrderStatus orderStatus,
        BigDecimal total
) {
    public OrderResponseDTO(Order order) {
        this(order.getId(), order.getOrderDate(), order.getOrderStatus(), order.getTotal());
    }
}
