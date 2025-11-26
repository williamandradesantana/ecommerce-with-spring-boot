package io.github.williamandradesantana.exceptions;

public class EntityFoundException extends RuntimeException {
    public EntityFoundException(String entity) {
        super(String.format("This %s already exists in database!", entity));
    }
}
