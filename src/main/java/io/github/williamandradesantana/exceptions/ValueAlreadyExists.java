package io.github.williamandradesantana.exceptions;


public class ValueAlreadyExists extends RuntimeException {
    public ValueAlreadyExists(String field) {
        super(String.format("This %s already exists.", field));
    }
}
