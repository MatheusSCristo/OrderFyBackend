package com.orderfy.backend.exceptions.customer;

import com.orderfy.backend.exceptions.ApiExceptionResponse;
import org.springframework.http.HttpStatus;

public class CustomerAlreadyExistsException extends ApiExceptionResponse {
    public CustomerAlreadyExistsException() {
        super("Cliente já cadastrado", HttpStatus.CONFLICT.value());
    }
}
