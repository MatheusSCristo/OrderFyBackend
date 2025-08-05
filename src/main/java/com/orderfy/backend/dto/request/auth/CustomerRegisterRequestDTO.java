package com.orderfy.backend.dto.request.auth;

import jakarta.validation.constraints.NotBlank;

public record CustomerRegisterRequestDTO(
        @NotBlank
        String name,
        @NotBlank
        String cpf
) {
}
