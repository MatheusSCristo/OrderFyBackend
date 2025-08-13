package com.orderfy.backend.exceptions.core;

import com.orderfy.backend.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class EntityAlreadyExistsException extends ApiException {


    public EntityAlreadyExistsException(String entityName, String fieldName, String value) {
        super(
                String.format("%s com %s '%s' já cadastrado(a).", entityName, fieldName, value),
                HttpStatus.CONFLICT.value()
        );
    }
}