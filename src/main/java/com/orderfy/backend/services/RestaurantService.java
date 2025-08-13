package com.orderfy.backend.services;

import com.orderfy.backend.dto.request.auth.RestaurantRegisterRequestDTO;
import com.orderfy.backend.enums.EmployeeRole;
import com.orderfy.backend.exceptions.ApiException;
import com.orderfy.backend.exceptions.core.EntityAlreadyExistsException;
import com.orderfy.backend.models.CustomerModel;
import com.orderfy.backend.models.EmployeeModel;
import com.orderfy.backend.models.RestaurantModel;
import com.orderfy.backend.repositories.EmployeeRepository;
import com.orderfy.backend.repositories.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final CustomerService customerService;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createRestaurant(RestaurantRegisterRequestDTO restaurantRegisterRequestDTO) {
        Optional<RestaurantModel> existingRestaurant = findRestaurantByCpfCnpj(restaurantRegisterRequestDTO.cnpjCpf());
        if (existingRestaurant.isPresent()) {
            throw new EntityAlreadyExistsException("Restaurante","CPF/CNPJ", restaurantRegisterRequestDTO.cnpjCpf());
        }

        Optional<EmployeeModel> existingEmployee = employeeRepository.findByCpf(restaurantRegisterRequestDTO.managerCpf());
        if (existingEmployee.isPresent()){
            throw new EntityAlreadyExistsException("Funcionário","CPF", restaurantRegisterRequestDTO.cnpjCpf());

        }

        Optional<CustomerModel> existingCustomer = customerService.findCustomer(restaurantRegisterRequestDTO.managerCpf());
        if (existingCustomer.isPresent()){
            throw new EntityAlreadyExistsException("Cliente","CPF", restaurantRegisterRequestDTO.cnpjCpf());

        }


        RestaurantModel restaurant = RestaurantModel.builder()
                .name(restaurantRegisterRequestDTO.name())
                .cnpjCpf(restaurantRegisterRequestDTO.cnpjCpf())
                .build();
        RestaurantModel savedRestaurant = restaurantRepository.save(restaurant);


        EmployeeModel manager = EmployeeModel.builder()
                .name(restaurantRegisterRequestDTO.managerName())
                .cpf(restaurantRegisterRequestDTO.managerCpf())
                .password(passwordEncoder.encode(restaurantRegisterRequestDTO.managerPassword()))
                .role(EmployeeRole.MANAGER)
                .restaurant(savedRestaurant)
                .build();
        employeeRepository.save(manager);
    }


    public Optional<RestaurantModel> findRestaurantByCpfCnpj(String cpfCnpj) {
        return restaurantRepository.findRestaurantByCnpjCpf(cpfCnpj);
    }


}
