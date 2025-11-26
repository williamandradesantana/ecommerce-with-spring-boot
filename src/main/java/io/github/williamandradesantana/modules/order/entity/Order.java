package io.github.williamandradesantana.modules.order.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.williamandradesantana.modules.order.dto.OrderRequestDTO;
import io.github.williamandradesantana.modules.order.dto.OrderResponseDTO;
import io.github.williamandradesantana.modules.order.entity.enums.OrderStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "order_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant orderDate;

    @Column(name = "order_status", nullable = false)
    private String orderStatus;

    @Column(name = "total", nullable = false, precision = 18, scale = 2)
    private BigDecimal total;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public Order(){}
    public Order(UUID id, Instant orderDate, String orderStatus, BigDecimal total, Boolean isActive, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.total = total;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Order(OrderRequestDTO dto) {
        this.orderDate = dto.orderDate();
        this.orderStatus = dto.orderStatus().getStatus();
        this.total = dto.total();
    }

    public Order(OrderResponseDTO dto) {
        this.id = dto.id();
        this.orderStatus = dto.orderStatus().getStatus();
        this.orderDate = dto.orderDate();
        this.total = dto.total();
    }

    public Order(UUID uuid) {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Instant getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Instant orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getOrderStatus() {
        return OrderStatus.fromString(this.orderStatus);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null) {
            this.orderStatus = orderStatus.getStatus();
        }
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
