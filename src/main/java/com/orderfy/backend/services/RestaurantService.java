package com.orderfy.backend.services;

import com.orderfy.backend.dto.request.auth.RestaurantRegisterRequestDTO;
import com.orderfy.backend.exceptions.restaurant.RestaurantAlreadyExistsException;
import com.orderfy.backend.models.CustomerModel;
import com.orderfy.backend.models.RestaurantModel;
import com.orderfy.backend.repositories.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final CustomerService customerService;

    public void createRestaurant(RestaurantRegisterRequestDTO restaurantRegisterRequestDTO) {
        Optional<RestaurantModel> existingRestaurant=findRestaurantByCpfCnpj(restaurantRegisterRequestDTO.cnpjCpf());
        Optional<CustomerModel> existingCustomer=customerService.findCustomer(restaurantRegisterRequestDTO.cnpjCpf());
        if (existingRestaurant.isPresent() || existingCustomer.isPresent() ) {
            throw new RestaurantAlreadyExistsException();
        }
        RestaurantModel restaurant = RestaurantModel.builder()
                .name(restaurantRegisterRequestDTO.name())
                .cnpjCpf(restaurantRegisterRequestDTO.cnpjCpf())
                .build();

        restaurantRepository.save(restaurant);

    }

    public Optional<RestaurantModel> findRestaurantByCpfCnpj(String cpfCnpj) {
        return restaurantRepository.findRestaurantByCnpjCpf(cpfCnpj);
    }


}
