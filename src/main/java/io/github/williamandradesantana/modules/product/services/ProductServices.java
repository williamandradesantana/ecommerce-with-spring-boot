package io.github.williamandradesantana.modules.product.services;

import io.github.williamandradesantana.exceptions.EntityNotFoundException;
import io.github.williamandradesantana.modules.product.dto.ProductRequestDTO;
import io.github.williamandradesantana.modules.product.dto.ProductResponseDTO;
import io.github.williamandradesantana.modules.product.entity.Product;
import io.github.williamandradesantana.modules.product.repository.ProductRepository;
import io.github.williamandradesantana.modules.product.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static io.github.williamandradesantana.utils.pagination.PaginationUtils.buildPagination;

@Service
public class ProductServices {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private ProductValidator validator;

    public Product create(ProductRequestDTO dto) {
        var product = new Product(dto);
        validator.validate(product);

        product.setActive(true);
        return repository.save(product);
    }

    public Page<ProductResponseDTO> findAll(Integer page, Integer pageSize) {
        Pageable pageableRequest = buildPagination(page, pageSize, "name");
        var products = repository.findByIsActiveTrue(pageableRequest);
        return products.map(ProductResponseDTO::new);
    }

    public Optional<Product> get(UUID id) {
        return repository.findById(id);
    }

    public void delete(UUID id) {
        var product = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("product", id));
        product.setActive(false);

        repository.save(product);
    }

    public ProductResponseDTO update(UUID id, ProductRequestDTO dto) {
        var product = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("product", id));

        product.setDescription(dto.description());
        product.setName(dto.name());
        product.setPrice(dto.price());
        product.setQuantity(dto.quantity());

        validator.validate(product);
        var saved = repository.save(product);

        return new ProductResponseDTO(saved);
    }
}
