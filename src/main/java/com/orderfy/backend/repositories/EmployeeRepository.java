package com.orderfy.backend.repositories;

import com.orderfy.backend.models.EmployeeModel;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends AbstractRepository<EmployeeModel> {
    Optional<EmployeeModel> findByCpf(String cpf);
}