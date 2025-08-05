package com.orderfy.backend.repositories;

import com.orderfy.backend.models.CustomerModel;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends AbstractRepository<CustomerModel> {

    Optional<CustomerModel> findByCpf(String cpf);
}
