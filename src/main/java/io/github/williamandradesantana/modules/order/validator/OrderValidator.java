package io.github.williamandradesantana.modules.order.validator;

import io.github.williamandradesantana.modules.order.entity.Order;
import io.github.williamandradesantana.modules.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class OrderValidator {

    @Autowired
    private OrderRepository repository;

    public void validate(Order order) {
        if (theOrderDateCannotBeInTheFuture(order))
            throw new IllegalArgumentException("The date order cannot be in the future");
    }

    private boolean theOrderDateCannotBeInTheFuture(Order order) {
        return order.getOrderDate().isAfter(Instant.now());
    }
}
