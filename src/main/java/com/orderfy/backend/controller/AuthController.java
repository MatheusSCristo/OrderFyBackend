package com.orderfy.backend.controller;

import com.orderfy.backend.dto.request.auth.CustomerRegisterRequestDTO;
import com.orderfy.backend.dto.request.auth.LoginRequestDTO;
import com.orderfy.backend.dto.response.auth.LoginResponseDTO;
import com.orderfy.backend.services.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO response=authService.login(loginRequestDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/customer/register")
    public ResponseEntity<LoginResponseDTO> customerRegister(@RequestBody @Valid CustomerRegisterRequestDTO customerRegisterRequestDTO) {
        LoginResponseDTO response=authService.registerCustomer(customerRegisterRequestDTO);
        return ResponseEntity.ok(response);
    }


}
