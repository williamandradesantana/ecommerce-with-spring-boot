package io.github.williamandradesantana.config;

import io.github.williamandradesantana.exceptions.EntityFoundException;
import io.github.williamandradesantana.exceptions.EntityNotFoundException;
import io.github.williamandradesantana.exceptions.ErrorResponse;
import io.github.williamandradesantana.exceptions.FieldNoQuantityCharsPresent;
import io.github.williamandradesantana.modules.order.entity.enums.OrderStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Arrays;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ErrorResponse(ex.getMessage(), HttpStatus.CONFLICT.value(), Instant.now().toString());
    }

    @ExceptionHandler(FieldNoQuantityCharsPresent.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleFieldNoQuantityCharsPresentException(FieldNoQuantityCharsPresent ex) {
        return new ErrorResponse(ex.getMessage(), HttpStatus.CONFLICT.value(), Instant.now().toString());
    }

    @ExceptionHandler(EntityFoundException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleEntityFoundException(EntityFoundException ex) {
        return new ErrorResponse(ex.getMessage(), HttpStatus.CONFLICT.value(), Instant.now().toString());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleEntityFoundException(EntityNotFoundException ex) {
        return new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value(), Instant.now().toString());
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleHttpMessageConversionException(HttpMessageConversionException ex) {
        String message = "Error processing the request body. Please check the values sent.";

        Throwable root = ex.getCause();

        if (root instanceof IllegalArgumentException && root.getMessage().contains("OrderStatus")) {
            message = "The 'orderStatus' field must be one of the valid values.: " +
                    Arrays.toString(OrderStatus.values());
        }

        return new ErrorResponse(message, HttpStatus.UNPROCESSABLE_ENTITY.value(), Instant.now().toString());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNullPointerException(NullPointerException ex) {
        return new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), Instant.now().toString());
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNullPointerException(IllegalStateException ex) {
        return new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), Instant.now().toString());
    }


}
