package io.github.williamandradesantana.modules.product.controller;

import io.github.williamandradesantana.common.controller.GenericController;
import io.github.williamandradesantana.modules.product.dto.ProductRequestDTO;
import io.github.williamandradesantana.modules.product.dto.ProductResponseDTO;
import io.github.williamandradesantana.modules.product.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController implements GenericController {

    @Autowired
    private ProductServices services;

    @PostMapping("/")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    public ResponseEntity<Void> create(@RequestBody ProductRequestDTO dto) {
        var newProduct = services.create(dto);
        URI location = createHeaderLocation(newProduct.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    public ResponseEntity<Page<ProductResponseDTO>> allProducts(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "4") Integer pageSize
    ) {
        var products = services.findAll(page, pageSize);
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    public ResponseEntity<ProductResponseDTO> get(@PathVariable("id") UUID id) {
        var product = services.get(id);

        return product.map((p) -> {
           var dto = new ProductResponseDTO(product.get());
           return ResponseEntity.ok().body(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable("id") UUID id, @RequestBody ProductRequestDTO dto) {
        var updated = services.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> delete(@PathVariable("id") UUID id) {
        return services.get(id).map((product -> {
            services.delete(id);
            return ResponseEntity.noContent().build();
        })).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
