package com.orderfy.backend.controller;

import com.orderfy.backend.dto.request.auth.EmployeeLoginRequestDTO;
import com.orderfy.backend.dto.request.auth.RestaurantRegisterRequestDTO;
import com.orderfy.backend.dto.response.auth.LoginResponseDTO;
import com.orderfy.backend.services.AuthService;
import com.orderfy.backend.services.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurants")
@AllArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final AuthService authService;

    @PostMapping("/create")
    public ResponseEntity<LoginResponseDTO> create(@RequestBody RestaurantRegisterRequestDTO restaurantRegisterRequestDTO){
        restaurantService.createRestaurant(restaurantRegisterRequestDTO);
        LoginResponseDTO response=authService.login(new EmployeeLoginRequestDTO(restaurantRegisterRequestDTO.managerCpf(),restaurantRegisterRequestDTO.managerPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
