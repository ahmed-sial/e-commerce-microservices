package com.ahmedhassan.ecommerce.exception;

import com.ahmedhassan.ecommerce.dto.ErrorResponse;
import com.ahmedhassan.ecommerce.exception.category.CategoryNotFoundException;
import com.ahmedhassan.ecommerce.exception.product.InsufficientProductStockQuantityException;
import com.ahmedhassan.ecommerce.exception.product.ProductAlreadyExistsWithNameException;
import com.ahmedhassan.ecommerce.exception.product.ProductNotFoundException;
import com.ahmedhassan.ecommerce.exception.product.ProductPurchaseException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.jspecify.annotations.NonNull;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private @NonNull ResponseEntity<ErrorResponse> buildErrorResponseEntity(
            @NonNull HttpStatus status,
            @NonNull Exception ex,
            @NonNull HttpServletRequest request) {
        var error = ErrorResponse
                .builder()
                .timeStamp(Instant.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerMethodArgumentNotValidException(
            @NonNull MethodArgumentNotValidException ex,
            @NonNull HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    var fieldName = (error instanceof FieldError fe) ? fe.getField() : error.getObjectName();
                    var message = error.getDefaultMessage();
                    errors.put(fieldName, message);
                });

        var status = HttpStatus.BAD_REQUEST;

        var error = ErrorResponse
                .builder()
                .timeStamp(Instant.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message("Validation failed")
                .path(request.getRequestURI())
                .details(errors)
                .build();
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationExceptionException(
            @NonNull ConstraintViolationException ex,
            @NonNull HttpServletRequest request) {
        return buildErrorResponseEntity(HttpStatus.BAD_REQUEST, ex, request);
    }

    public ResponseEntity<ErrorResponse> handleBindException(
            @NonNull BindException ex,
            @NonNull HttpServletRequest request) {
        return buildErrorResponseEntity(HttpStatus.BAD_REQUEST, ex, request);
    }

    // JSON parsing error or malformed request body
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
            @NonNull HttpMessageNotReadableException ex,
            @NonNull HttpServletRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var error = ErrorResponse
                .builder()
                .timeStamp(Instant.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message("Unable to process request. Kindly provide a valid request")
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(status).body(error);
    }

    // Error while serializing response body
    @ExceptionHandler(HttpMessageNotWritableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotWritableException(
            @NonNull HttpMessageNotWritableException ex,
            @NonNull HttpServletRequest request) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        var error = ErrorResponse
                .builder()
                .timeStamp(Instant.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message("An unexpected error occurred on our side. Please try again later")
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(status).body(error);
    }

    // Required request parameter missing
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(
            @NonNull MissingServletRequestParameterException ex,
            @NonNull HttpServletRequest request) {
        return buildErrorResponseEntity(HttpStatus.BAD_REQUEST, ex, request);
    }

    // Path variable missing in request mapping
    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<ErrorResponse> handleMissingPathVariableException(
            @NonNull MissingPathVariableException ex,
            @NonNull HttpServletRequest request) {
        return buildErrorResponseEntity(HttpStatus.BAD_REQUEST, ex, request);
    }

    // Type mismatch (e.g. String passed where Long expected)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(
            @NonNull MethodArgumentTypeMismatchException ex,
            @NonNull HttpServletRequest request) {
        return buildErrorResponseEntity(HttpStatus.BAD_REQUEST, ex, request);
    }

    // HTTP method not supported for endpoint
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
            @NonNull HttpRequestMethodNotSupportedException ex,
            @NonNull HttpServletRequest request) {
        return buildErrorResponseEntity(HttpStatus.METHOD_NOT_ALLOWED, ex, request);
    }

    // Unsupported content type (e.g. XML sent when JSON expected)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpMediaTypeNotSupportedException(
            @NonNull HttpMediaTypeNotSupportedException ex,
            @NonNull HttpServletRequest request) {
        return buildErrorResponseEntity(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex, request);
    }

    // Database constraint violation (unique key, foreign key, etc.)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(
            @NonNull DataIntegrityViolationException ex,
            @NonNull HttpServletRequest request) {
        return buildErrorResponseEntity(HttpStatus.CONFLICT,
                new RuntimeException("A data conflict occurred. Please check your input"), request);
    }

    // Illegal argument passed to method
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            @NonNull IllegalArgumentException ex,
            @NonNull HttpServletRequest request) {
        return buildErrorResponseEntity(HttpStatus.BAD_REQUEST, ex, request);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(
            @NonNull CategoryNotFoundException ex,
            @NonNull HttpServletRequest request
    ) {
        return buildErrorResponseEntity(HttpStatus.NOT_FOUND, ex, request);
    }

    @ExceptionHandler(ProductAlreadyExistsWithNameException.class)
    public ResponseEntity<ErrorResponse> handleProductNameAlreadyExistsException(
            @NonNull ProductAlreadyExistsWithNameException ex,
            @NonNull HttpServletRequest request
    ) {
        return buildErrorResponseEntity(HttpStatus.CONFLICT, ex, request);
    }

    @ExceptionHandler(InsufficientProductStockQuantityException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientProductStockQuantityException(
            @NonNull InsufficientProductStockQuantityException ex,
            @NonNull HttpServletRequest request
    ) {
        return buildErrorResponseEntity(HttpStatus.CONFLICT, ex, request);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(
            @NonNull ProductNotFoundException ex,
            @NonNull HttpServletRequest request
    ) {
        return buildErrorResponseEntity(HttpStatus.NOT_FOUND, ex, request);
    }

    @ExceptionHandler(ProductPurchaseException.class)
    public ResponseEntity<ErrorResponse> handleProductPurchaseException(
            @NonNull ProductPurchaseException ex,
            @NonNull HttpServletRequest request
    ) {
        return buildErrorResponseEntity(HttpStatus.BAD_REQUEST, ex, request);
    }

    // Catch-all fallback for any unhandled exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            @NonNull Exception ex,
            @NonNull HttpServletRequest request) {
        return buildErrorResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,
                new RuntimeException("An unexpected error occurred on our side. Please try again later"),
                request);
    }
}
