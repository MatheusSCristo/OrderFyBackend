package com.orderfy.auth_service.Restaurant.dto;

public record RestaurantRequestCreateDTO(
        String cnpj,
        String name
) {
}
