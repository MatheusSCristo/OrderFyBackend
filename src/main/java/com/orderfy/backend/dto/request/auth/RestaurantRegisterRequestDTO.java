package com.orderfy.backend.dto.request.auth;

import jakarta.validation.constraints.NotBlank;

public record RestaurantRegisterRequestDTO(
        @NotBlank
        String name,
        @NotBlank
        String cnpjCpf
) {
}
