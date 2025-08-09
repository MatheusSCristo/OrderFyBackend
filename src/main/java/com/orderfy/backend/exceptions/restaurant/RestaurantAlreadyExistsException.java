package com.orderfy.backend.exceptions.restaurant;

import com.orderfy.backend.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class RestaurantAlreadyExistsException extends ApiException {
    public RestaurantAlreadyExistsException() {
        super("Restaurante já cadastrado", HttpStatus.CONFLICT.value());
    }
}
