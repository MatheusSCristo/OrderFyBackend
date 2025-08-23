package com.orderfy.backend.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerAuthRequestDTO(
        @NotBlank
        String name,
        @NotNull
        String tableId
        ) {}
