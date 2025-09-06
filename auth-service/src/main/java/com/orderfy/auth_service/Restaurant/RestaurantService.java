package com.orderfy.auth_service.Restaurant;

import com.orderfy.auth_service.Restaurant.dto.RestaurantRequestCreateDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.orderfy.auth_service.Utils.FormatCpfCnpj.formatCnpj;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public void create(RestaurantRequestCreateDTO restaurant){

        String cnpj=formatCnpj(restaurant.cnpj());
        Restaurant newRestaurant=new Restaurant();
        newRestaurant.setCnpj(cnpj);
        newRestaurant.setName(restaurant.name());
        restaurantRepository.save(newRestaurant);

    }

    public Restaurant findById(String id){
        return restaurantRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Restaurante não encontrado"));
    }




}
