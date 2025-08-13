package com.orderfy.backend.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record RestaurantRegisterRequestDTO(
        @NotBlank
        String name,
        @NotBlank
        String cnpjCpf,
        @NotBlank
        String managerName,

        @CPF
        @NotBlank
        String managerCpf,

        @NotBlank
        @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
        String managerPassword
) {
}