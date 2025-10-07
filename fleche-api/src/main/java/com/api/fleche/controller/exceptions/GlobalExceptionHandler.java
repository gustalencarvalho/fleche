package com.api.fleche.controller.exceptions;

import com.api.fleche.model.dtos.StandardError;
import com.api.fleche.model.dtos.ValidationException;
import com.api.fleche.model.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LocationNotFoundException.class)
    ResponseEntity<StandardError> handleCnpjAlreadyExists(final LocationNotFoundException ex, final HttpServletRequest request) {
        return ResponseEntity.status(NOT_FOUND).body(StandardError.builder()
                .timestamp(now())
                .status(NOT_FOUND.value())
                .error(NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    ResponseEntity<StandardError> handleEmailAlreadyExists(final EmailAlreadyExistsException ex, final HttpServletRequest request) {
        return ResponseEntity.status(CONFLICT).body(StandardError.builder()
                .timestamp(now())
                .status(CONFLICT.value())
                .error(CONFLICT.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build());
    }

    @ExceptionHandler(AgeMinNotDifinedException.class)
    ResponseEntity<StandardError> handleAgeMinNotDifinedException(final AgeMinNotDifinedException ex, final HttpServletRequest request) {
        return ResponseEntity.status(CONFLICT).body(StandardError.builder()
                .timestamp(now())
                .status(CONFLICT.value())
                .error(CONFLICT.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build());
    }

    @ExceptionHandler(PhoneAlreadyExistsException.class)
    ResponseEntity<StandardError> handlePhoneAlreadyExistsException(final PhoneAlreadyExistsException ex, final HttpServletRequest request) {
        return ResponseEntity.status(CONFLICT).body(StandardError.builder()
                .timestamp(now())
                .status(CONFLICT.value())
                .error(CONFLICT.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build());
    }

    @ExceptionHandler(UserNotFounException.class)
    ResponseEntity<StandardError> handleUserNotFounException(final UserNotFounException ex, final HttpServletRequest request) {
        return ResponseEntity.status(NOT_FOUND).body(StandardError.builder()
                .timestamp(now())
                .status(NOT_FOUND.value())
                .error(NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<StandardError> handleMethodArgumentNotValidException(final MethodArgumentNotValidException ex, final HttpServletRequest request) {
        var error = ValidationException.builder()
                .timestamp(now())
                .status(BAD_REQUEST.value())
                .error("Validation Exception")
                .message("Exception in validation attributes")
                .path(request.getRequestURI())
                .erros(new ArrayList<>())
                .build();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            error.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(UserOnlineInOtherLocalException.class)
    ResponseEntity<StandardError> handleUserOnlineInOtherLocalException(final UserOnlineInOtherLocalException ex, final HttpServletRequest request) {
        return ResponseEntity.status(CONFLICT).body(StandardError.builder()
                .timestamp(now())
                .status(CONFLICT.value())
                .error(CONFLICT.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build());
    }
}