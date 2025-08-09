package com.orderfy.backend.repositories;

import com.orderfy.backend.models.RestaurantModel;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends AbstractRepository<RestaurantModel> {


    Optional<RestaurantModel> findRestaurantByCnpjCpf(String cnpjCpf);
}
