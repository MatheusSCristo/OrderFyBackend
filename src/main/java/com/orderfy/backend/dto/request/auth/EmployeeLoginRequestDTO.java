package com.orderfy.backend.dto.request.auth;


import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record EmployeeLoginRequestDTO(
        @CPF
        @NotNull
        String cpf,
        String password
) {
}
