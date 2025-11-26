package io.github.williamandradesantana.modules.orderitem.dto;

import io.github.williamandradesantana.modules.orderitem.entity.OrderItem;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemRequestDTO(
        UUID productID,
        UUID orderID,
        Integer quantity,
        BigDecimal unitPrice
) {
    public OrderItemRequestDTO(OrderItem entity) {
        this(entity.getOrderID().getId(), entity.getProductID().getId(), entity.getQuantity(), entity.getUnitPrice());
    }
}
