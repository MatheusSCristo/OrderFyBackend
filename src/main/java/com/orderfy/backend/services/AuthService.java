package com.orderfy.backend.services;

import com.orderfy.backend.dto.request.auth.CustomerRegisterRequestDTO;
import com.orderfy.backend.dto.request.auth.LoginRequestDTO;
import com.orderfy.backend.dto.response.auth.LoginResponseDTO;
import com.orderfy.backend.exceptions.customer.CustomerAlreadyExistsException;
import com.orderfy.backend.models.CustomerModel;
import com.orderfy.backend.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;

    public LoginResponseDTO login(LoginRequestDTO request) {
        UserDetails user;

        if (request.password() == null || request.password().isEmpty()) {
            user = customerRepository.findByCpf(request.cpf())
                    .orElseThrow(() -> new UsernameNotFoundException("Customer not found"));
        } else {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.cpf(),
                            request.password()
                    )
            );
            user = userDetailsService.loadUserByUsername(request.cpf());
        }

        String jwt = jwtService.generateToken(user);
        long expirationTime = jwtService.getExpirationTime();

        return new LoginResponseDTO(jwt, expirationTime);
    }
    public LoginResponseDTO registerCustomer(CustomerRegisterRequestDTO register) {
        customerService.createCustomer(register);
        return login(new LoginRequestDTO(register.cpf(),null));


    }


}
