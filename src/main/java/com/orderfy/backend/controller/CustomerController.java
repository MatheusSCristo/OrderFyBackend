package com.orderfy.backend.controller;

import com.orderfy.backend.dto.request.auth.CustomerAuthRequestDTO;
import com.orderfy.backend.dto.response.auth.LoginResponseDTO;
import com.orderfy.backend.services.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> customerRegister(@RequestBody @Valid CustomerAuthRequestDTO customerRegisterRequestDTO) {
        LoginResponseDTO response=authService.authCustomer(customerRegisterRequestDTO);
        return ResponseEntity.ok(response);
    }
}
