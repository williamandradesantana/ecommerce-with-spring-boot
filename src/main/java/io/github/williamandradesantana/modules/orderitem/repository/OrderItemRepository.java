package io.github.williamandradesantana.modules.orderitem.repository;

import io.github.williamandradesantana.modules.orderitem.entity.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
    Page<OrderItem> findByIsActiveTrue(Pageable pageable);
}
