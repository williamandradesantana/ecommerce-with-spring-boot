package io.github.williamandradesantana.exceptions;

public record ErrorResponse(String message, int status, String timestamp) {
}
