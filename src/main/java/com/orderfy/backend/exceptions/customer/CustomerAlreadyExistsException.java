package com.orderfy.backend.exceptions.customer;

import com.orderfy.backend.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class CustomerAlreadyExistsException extends ApiException {
    public CustomerAlreadyExistsException() {
        super("Cliente já cadastrado", HttpStatus.CONFLICT.value());
    }
}
