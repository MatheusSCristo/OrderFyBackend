package com.orderfy.backend.controller;

import com.orderfy.backend.dto.request.auth.RestaurantRegisterRequestDTO;
import com.orderfy.backend.services.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurants")
@AllArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody RestaurantRegisterRequestDTO restaurantRegisterRequestDTO){
        restaurantService.createRestaurant(restaurantRegisterRequestDTO);
        return ResponseEntity.ok().build();
    }
}
