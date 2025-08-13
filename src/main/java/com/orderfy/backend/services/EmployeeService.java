package com.orderfy.backend.services;

import com.orderfy.backend.dto.request.auth.EmployeeRegisterRequestDTO;
import com.orderfy.backend.exceptions.ApiException;
import com.orderfy.backend.exceptions.core.EntityAlreadyExistsException;
import com.orderfy.backend.models.EmployeeModel;
import com.orderfy.backend.models.RestaurantModel;
import com.orderfy.backend.repositories.EmployeeRepository;
import com.orderfy.backend.repositories.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RestaurantRepository restaurantRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeModel createEmployee(Long restaurantId, EmployeeRegisterRequestDTO employeeDTO) {
        RestaurantModel restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurante não encontrado com o ID: " + restaurantId));

        Optional<EmployeeModel> existingEmployee = employeeRepository.findByCpf(employeeDTO.cpf());
        if (existingEmployee.isPresent()) {
            throw new EntityAlreadyExistsException("Funcionário","CPF", employeeDTO.cpf());
        }

        EmployeeModel newEmployee = EmployeeModel.builder()
                .name(employeeDTO.name())
                .cpf(employeeDTO.cpf())
                .password(passwordEncoder.encode(employeeDTO.password())) // Criptografa a senha
                .role(employeeDTO.role())
                .restaurant(restaurant)
                .build();

        return employeeRepository.save(newEmployee);
    }
}