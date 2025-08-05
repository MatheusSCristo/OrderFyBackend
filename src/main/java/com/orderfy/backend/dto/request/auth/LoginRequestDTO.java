package com.orderfy.backend.dto.request.auth;


import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record LoginRequestDTO(
        @CPF
        @NotNull
        String cpf,
        String password

) {
}
