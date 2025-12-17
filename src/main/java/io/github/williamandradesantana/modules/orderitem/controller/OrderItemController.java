package io.github.williamandradesantana.modules.orderitem.controller;

import io.github.williamandradesantana.common.controller.GenericController;
import io.github.williamandradesantana.modules.orderitem.dto.OrderItemRequestDTO;
import io.github.williamandradesantana.modules.orderitem.dto.OrderItemResponseDTO;
import io.github.williamandradesantana.modules.orderitem.services.OrderItemServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/ordersItems")
@PreAuthorize("hasAuthority('ADMIN')")
@Tag(name= "Orders Items")
public class OrderItemController implements GenericController {

    @Autowired
    private OrderItemServices orderItemServices;

    @PostMapping("/")
    public ResponseEntity<Void> create(@RequestBody OrderItemRequestDTO dto) {
        var orderItem = orderItemServices.create(dto);
        URI location = createHeaderLocation(orderItem.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/")
    public ResponseEntity<Page<OrderItemResponseDTO>> findAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "4") Integer pageSize
    ) {
        var ordersItems = orderItemServices.findAll(page, pageSize);
        return ResponseEntity.ok().body(ordersItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemResponseDTO> get(@PathVariable("id") UUID id) {
        var orderItem = orderItemServices.get(id);
        return orderItem.map(oI -> {
            var dto = new OrderItemResponseDTO(orderItem.get());
            return ResponseEntity.ok().body(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") UUID id) {
        return orderItemServices.get(id).map(orderItem -> {
            orderItemServices.delete(id);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
