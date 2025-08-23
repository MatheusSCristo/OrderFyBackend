package com.orderfy.backend.configs;

import com.orderfy.backend.repositories.CustomerRepository; // Importe o repositório do cliente
import com.orderfy.backend.repositories.EmployeeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AuthConfig {

    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;

    public AuthConfig(EmployeeRepository employeeRepository, CustomerRepository customerRepository) {
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
    }

    @Bean
    UserDetailsService userDetailsService() {
        return username -> {
            var employeeDetails = employeeRepository.findByCpf(username)
                    .map(employee -> (UserDetails) employee);

            if (employeeDetails.isPresent()) {
                return employeeDetails.get();
            }

            try {

                return customerRepository.findByIdentifyingId(username)
                        .map(customer -> (UserDetails) customer)
                        .orElseThrow(() -> new UsernameNotFoundException("Utilizador não encontrado com o ID: " + username));

            } catch (NumberFormatException e) {
                throw new UsernameNotFoundException("Formato de identificador inválido para o utilizador: " + username);
            }
        };
    }
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}