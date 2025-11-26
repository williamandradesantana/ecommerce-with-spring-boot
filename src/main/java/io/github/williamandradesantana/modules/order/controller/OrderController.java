package io.github.williamandradesantana.modules.order.controller;

import io.github.williamandradesantana.common.controller.GenericController;
import io.github.williamandradesantana.modules.order.dto.OrderRequestDTO;
import io.github.williamandradesantana.modules.order.dto.OrderResponseDTO;
import io.github.williamandradesantana.modules.order.entity.Order;
import io.github.williamandradesantana.modules.order.services.OrderExportServices;
import io.github.williamandradesantana.modules.order.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
public class OrderController implements GenericController {

    @Autowired
    private OrderServices services;
    @Autowired
    private OrderExportServices exportServices;

    @PostMapping("/")
    public ResponseEntity<Void> create(@RequestBody OrderRequestDTO dto) {
        var order = services.create(dto);
        URI location = createHeaderLocation(order.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> get(@PathVariable("id") UUID id) {
        var order = services.get(id);
        return order.map(o -> {
            var dto = new OrderResponseDTO(order.get());
            return ResponseEntity.ok().body(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public ResponseEntity<Page<OrderResponseDTO>> findAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "4") Integer pageSize
    ) {
        var orders = services.findAllPagination(page, pageSize);
        return ResponseEntity.ok().body(orders);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> update(
            @PathVariable("id") UUID id,
            @RequestBody OrderRequestDTO dto,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "4") Integer pageSize
    ) {
        var updated = services.update(id, dto, page, pageSize);
        return ResponseEntity.ok().body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") UUID id) {
        return services.get(id).map(order -> {
            services.delete(id);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/export")
    public ResponseEntity<Resource> exportOrders() throws IOException {
        List<Order> orders = services.findAll();

        ByteArrayInputStream inputStream = exportServices.exportOrdersToExcel(orders);
        InputStreamResource file = new InputStreamResource(inputStream);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=orders.xlsx")
                .contentType(MediaType.parseMediaType(
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                )).body(file);
    }
}
