package com.orderfy.backend.controller;

import com.orderfy.backend.dto.request.auth.EmployeeRegisterRequestDTO;
import com.orderfy.backend.models.EmployeeModel;
import com.orderfy.backend.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/restaurants/{restaurantId}/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeModel> create(
            @PathVariable Long restaurantId,
            @RequestBody @Valid EmployeeRegisterRequestDTO employeeRegisterRequestDTO) {

        EmployeeModel createdEmployee = employeeService.createEmployee(restaurantId, employeeRegisterRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }
}