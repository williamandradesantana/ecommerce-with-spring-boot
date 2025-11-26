package io.github.williamandradesantana.modules.product.dto;

import io.github.williamandradesantana.modules.product.entity.Product;

import java.math.BigDecimal;

public record ProductRequestDTO(
        String name,
        String description,
        BigDecimal price,
        Integer quantity
) {
    public ProductRequestDTO(Product entity) {
        this(entity.getName(), entity.getDescription(), entity.getPrice(), entity.getQuantity());
    }
}
