package com.orderfy.backend.exceptions;

import org.springframework.http.HttpStatus;

public interface ApiExceptionInterface {
    HttpStatus getStatus();
}
