package com.orderfy.auth_service.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByCpfAndRestaurantId(String cpf, String restaurantId);
}
