package io.github.williamandradesantana.modules.orderitem.dto;

import io.github.williamandradesantana.modules.orderitem.entity.OrderItem;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemResponseDTO(
        UUID id,
        UUID productID,
        UUID orderID,
        Integer quantity,
        BigDecimal unitPrice
) {
    public OrderItemResponseDTO(OrderItem entity) {
        this(entity.getId(), entity.getOrderID().getId(), entity.getProductID().getId(), entity.getQuantity(), entity.getUnitPrice());
    }
}
