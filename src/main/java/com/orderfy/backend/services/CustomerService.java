package com.orderfy.backend.services;

import com.orderfy.backend.dto.request.auth.CustomerRegisterRequestDTO;
import com.orderfy.backend.exceptions.core.EntityAlreadyExistsException;
import com.orderfy.backend.models.CustomerModel;
import com.orderfy.backend.repositories.CustomerRepository;
import com.orderfy.backend.utils.Formatter;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerModel findCustomerOrThrow(String cpf){
        String formattedCpf = Formatter.formatCpfCnpj(cpf);
        return findCustomer(formattedCpf).orElseThrow(()->new EntityNotFoundException("Customer with CPF: "+cpf+" not found"));
    }

    public Optional<CustomerModel> findCustomer(String cpf){
        String formattedCpf = Formatter.formatCpfCnpj(cpf);
        return customerRepository.findByCpf(formattedCpf);
    }

    public void createCustomer(CustomerRegisterRequestDTO customer){
        String formattedCpf = Formatter.formatCpfCnpj(customer.cpf());
        Optional<CustomerModel> existing = findCustomer(formattedCpf);
        if (existing.isPresent()) {
            throw new EntityAlreadyExistsException("Cliente","CPF",customer.cpf());
        }
        CustomerModel newCustomer = CustomerModel.builder()
                .name(customer.name())
                .cpf(formattedCpf)
                .build();
        customerRepository.save(newCustomer);
    }


}
