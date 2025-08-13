package com.orderfy.backend.dto.request.auth;

import com.orderfy.backend.enums.EmployeeRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record EmployeeRegisterRequestDTO(
        @NotBlank
        String name,
        @CPF
        @NotBlank
        String cpf,
        @NotBlank
        String password,
        @NotNull
        EmployeeRole role
) {
}