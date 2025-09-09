package com.orderfy.auth_service.core.exceptions;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{
    private String message;
    private HttpStatus status;
    private final long timestamp= System.currentTimeMillis();
}
