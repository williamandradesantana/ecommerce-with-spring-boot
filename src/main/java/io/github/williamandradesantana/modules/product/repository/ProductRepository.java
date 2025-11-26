package io.github.williamandradesantana.modules.product.repository;

import io.github.williamandradesantana.modules.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByName(String name);
    Page<Product> findByIsActiveTrue(Pageable pageable);
}
