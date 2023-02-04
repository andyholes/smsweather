package com.andyholes.smsweather.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;


import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
                Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

        @ExceptionHandler(value = {NotFoundException.class})
        protected ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException ex,
                WebRequest request) {
            ErrorMessage message = new ErrorMessage(
                    HttpStatus.NOT_FOUND.value(),
                    new Date(),
                    ex.getMessage(),
                    request.getDescription(false));

            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    @ExceptionHandler(value = {InvalidCodeException.class})
    protected ResponseEntity<ErrorMessage> handleInvalidCodeException(InvalidCodeException ex,
                                                                   WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {NotSentException.class})
    protected ResponseEntity<ErrorMessage> handleNotSentException(NotSentException ex,
                                                                   WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

        @ExceptionHandler(value = {Exception.class})
        protected ResponseEntity<ErrorMessage> handleGlobalException(Exception ex, WebRequest request) {

            ErrorMessage message = new ErrorMessage(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    new Date(),
                    ex.getMessage(),
                    request.getDescription(false));

            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }