package io.github.williamandradesantana.modules.order.services;

import io.github.williamandradesantana.exceptions.EntityNotFoundException;
import io.github.williamandradesantana.modules.order.dto.OrderRequestDTO;
import io.github.williamandradesantana.modules.order.dto.OrderResponseDTO;
import io.github.williamandradesantana.modules.order.entity.Order;
import io.github.williamandradesantana.modules.order.repository.OrderRepository;
import io.github.williamandradesantana.modules.order.validator.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.github.williamandradesantana.utils.pagination.PaginationUtils.buildPagination;

@Service
public class OrderServices {

    @Autowired
    private OrderRepository repository;
    @Autowired
    private OrderValidator validator;

    public Order create(OrderRequestDTO dto) {
        var order = new Order(dto);
        validator.validate(order);
        order.setActive(true);
        return repository.save(order);
    }

    public Optional<Order> get(UUID id) {
        return repository.findById(id);
    }

    public Page<OrderResponseDTO> findAllPagination(Integer page, Integer pageSize) {
        Pageable pageable = buildPagination(page, pageSize, "orderDate");
        var orders = repository.findByIsActiveTrue(pageable);
        return orders.map(OrderResponseDTO::new);
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    public OrderResponseDTO update(UUID id, OrderRequestDTO dto, Integer page, Integer pageSize) {
        Pageable pageable = buildPagination(page, pageSize, "orderDate");
        Page<UUID> pageIds = repository.findActiveIds(pageable);

        if (!pageIds.getContent().contains(id)) {
            throw new IllegalStateException("The order is not on the current page, so it cannot be changed.");
        }

        var order = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("order", id));
        order.setOrderDate(dto.orderDate());
        order.setOrderStatus(dto.orderStatus());
        order.setTotal(dto.total());

        validator.validate(order);
        var saved = repository.save(order);
        return new OrderResponseDTO(saved);
    }

    public void delete(UUID id) {
        var order = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("order", id));
        order.setActive(false);
        repository.save(order);
    }
}
