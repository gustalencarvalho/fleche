package com.api.fleche.controller.exceptions;

import com.api.fleche.model.dtos.StandardError;
import com.api.fleche.model.dtos.ValidationException;
import com.api.fleche.model.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LocationNotFoundException.class)
    ProblemDetail handleLocationNotFoundExceptionHandler(final LocationNotFoundException ex, final HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setType(URI.create("http://localhost:8085/"));
        problem.setTitle("Location not found");
        problem.setDetail(ex.getMessage());
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(UserNotFounException.class)
    ProblemDetail handleUserNotFounExceptionHandler(final UserNotFounException ex, final HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setType(URI.create("http://localhost:8085/"));
        problem.setTitle("User not found");
        problem.setDetail(ex.getMessage());
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    ProblemDetail handleEmailAlreadyExistsHandler(final EmailAlreadyExistsException ex, final HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatus(CONFLICT);
        problem.setType(URI.create("http://localhost:8085/"));
        problem.setTitle("E-mail exists");
        problem.setDetail(ex.getMessage());
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(AgeMinNotDifinedException.class)
    ProblemDetail handleAgeMinNotDifinedExceptionHandler(final AgeMinNotDifinedException ex, final HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problem.setType(URI.create("http://localhost:8085/"));
        problem.setTitle("User min age 18");
        problem.setDetail(ex.getMessage());
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(PhoneAlreadyExistsException.class)
    ProblemDetail handlePhoneAlreadyExistsExceptionHandler(final PhoneAlreadyExistsException ex, final HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problem.setType(URI.create("http://localhost:8085/"));
        problem.setTitle("Phone number");
        problem.setDetail(ex.getMessage());
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ProblemDetail handleMethodArgumentNotValidExceptionHandler(final MethodArgumentNotValidException ex, final HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setType(URI.create("http://localhost:8085/problems/validation-error"));
        problem.setTitle("Validation failed");

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();

        problem.setProperty("errors", errors);
        return problem;
    }

    @ExceptionHandler(UserOnlineInOtherLocalException.class)
    ProblemDetail handleUserOnlineInOtherLocalExceptionHandler(final UserOnlineInOtherLocalException ex, final HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problem.setType(URI.create("http://localhost:8085/"));
        problem.setTitle("User online");
        problem.setDetail(ex.getMessage());
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

}