package com.orderfy.backend.services;

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

    public CustomerModel findCustomerOrThrow(String identifyingId,String name){
        return findCustomer(identifyingId,name).orElseThrow(()->new EntityNotFoundException
                ("Cliente com nome: "+name+" não encontrado"));
    }

    public Optional<CustomerModel> findCustomer(String identifyingId,String name){
        return customerRepository.findByIdentifyingIdAndName(identifyingId,name);
    }



}
