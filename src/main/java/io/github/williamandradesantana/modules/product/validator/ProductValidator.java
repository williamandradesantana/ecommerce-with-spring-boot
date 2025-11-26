package io.github.williamandradesantana.modules.product.validator;

import io.github.williamandradesantana.exceptions.EntityFoundException;
import io.github.williamandradesantana.exceptions.FieldNoQuantityCharsPresent;
import io.github.williamandradesantana.modules.product.entity.Product;
import io.github.williamandradesantana.modules.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {

    @Autowired
    private ProductRepository repository;

    public void validate(Product product) {
        if (productNameAlreadyExists(product)) throw new EntityFoundException("product");
        validateMinChars(product.getName(), "username", 1);
    }

    private boolean productNameAlreadyExists(Product product) {
        return repository.findByName(product.getName())
                .filter(p -> !p.getId().equals(product.getId()))
                .isPresent();
    }

    private void validateMinChars(String value, String field, int minChars) {
        if (value == null || value.trim().length() < minChars)
            throw new FieldNoQuantityCharsPresent(field, minChars);
    }
}
