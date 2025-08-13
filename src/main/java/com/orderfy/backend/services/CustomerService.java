package com.orderfy.backend.services;

import com.orderfy.backend.dto.request.auth.CustomerRegisterRequestDTO;
import com.orderfy.backend.exceptions.core.EntityAlreadyExistsException;
import com.orderfy.backend.models.CustomerModel;
import com.orderfy.backend.repositories.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerModel findCustomerOrThrow(String cpf){
        return findCustomer(cpf).orElseThrow(()->new EntityNotFoundException("Customer with CPF: "+cpf+" not found"));
    }

    public Optional<CustomerModel> findCustomer(String cpf){
        return customerRepository.findByCpf(cpf);
    }

    public void createCustomer(CustomerRegisterRequestDTO customer){
        Optional<CustomerModel> existing = findCustomer(customer.cpf());
        if (existing.isPresent()) {
            throw new EntityAlreadyExistsException("Cliente","CPF",customer.cpf());
        }
        CustomerModel newCustomer = CustomerModel.builder()
                .name(customer.name())
                .cpf(customer.cpf())
                .build();
        customerRepository.save(newCustomer);
    }


}
