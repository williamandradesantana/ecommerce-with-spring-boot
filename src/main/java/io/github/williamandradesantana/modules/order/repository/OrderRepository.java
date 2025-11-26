package io.github.williamandradesantana.modules.order.repository;

import io.github.williamandradesantana.modules.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    Page<Order> findByIsActiveTrue(Pageable pageable);

    @Query("select o.id from Order o where o.isActive = true")
    Page<UUID> findActiveIds(Pageable pageable);
}
