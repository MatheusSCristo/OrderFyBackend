package com.orderfy.auth_service.Employee.dto;

import com.orderfy.auth_service.Employee.EmployeeRole;

public record EmployeeCreateRequestDTO(
        String name,
        String lastName,
        String cpf,
        String password,
        EmployeeRole role,
        String restaurantId
) {
}
