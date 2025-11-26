package io.github.williamandradesantana.exceptions;


public class FieldNoQuantityCharsPresent extends RuntimeException {
    public FieldNoQuantityCharsPresent(String field, int quantity) {
        super(String.format("The field '%s' must contain at least %d characters.", field, quantity));
    }
}
