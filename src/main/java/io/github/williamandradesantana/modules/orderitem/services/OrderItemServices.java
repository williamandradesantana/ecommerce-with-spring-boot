package io.github.williamandradesantana.modules.orderitem.services;

import io.github.williamandradesantana.exceptions.EntityNotFoundException;
import io.github.williamandradesantana.modules.order.repository.OrderRepository;
import io.github.williamandradesantana.modules.orderitem.dto.OrderItemRequestDTO;
import io.github.williamandradesantana.modules.orderitem.dto.OrderItemResponseDTO;
import io.github.williamandradesantana.modules.orderitem.entity.OrderItem;
import io.github.williamandradesantana.modules.orderitem.repository.OrderItemRepository;
import io.github.williamandradesantana.modules.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static io.github.williamandradesantana.utils.pagination.PaginationUtils.buildPagination;

@Service
public class OrderItemServices {

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    public OrderItem create(OrderItemRequestDTO dto) {
        var product = productRepository.findById(dto.productID())
                .orElseThrow(() -> new EntityNotFoundException("product", dto.productID()));
        var order = orderRepository.findById(dto.orderID())
                .orElseThrow(() -> new EntityNotFoundException("order", dto.orderID()));

        var orderItem = new OrderItem(dto, product, order);

        orderItem.setActive(true);
        return orderItemRepository.save(orderItem);
    }

    public Page<OrderItemResponseDTO> findAll(Integer page, Integer pageSize) {
        Pageable pageable = buildPagination(page, pageSize, "createdAt");
        var ordersItems = orderItemRepository.findByIsActiveTrue(pageable);
        return ordersItems.map(OrderItemResponseDTO::new);
    }

    public Optional<OrderItem> get(UUID id) {
        return orderItemRepository.findById(id);
    }

    public void delete(UUID id) {
        var orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("orderItem", id));
        orderItem.setActive(false);
        orderItemRepository.save(orderItem);
    }
}
