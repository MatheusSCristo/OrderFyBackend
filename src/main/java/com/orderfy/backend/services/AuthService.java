package com.orderfy.backend.services;

import com.orderfy.backend.dto.request.auth.CustomerAuthRequestDTO;
import com.orderfy.backend.dto.request.auth.EmployeeLoginRequestDTO;
import com.orderfy.backend.dto.response.auth.LoginResponseDTO;
import com.orderfy.backend.enums.TabStatus;
import com.orderfy.backend.models.CustomerModel;
import com.orderfy.backend.models.TabModel;
import com.orderfy.backend.models.TableModel;
import com.orderfy.backend.repositories.CustomerRepository;
import com.orderfy.backend.utils.Formatter;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final TableService tableService;
    private final TabService tabService;


    public LoginResponseDTO login(EmployeeLoginRequestDTO request) {
        UserDetails user;

        String formattedCpf=Formatter.formatCpfCnpj(request.cpf());

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            formattedCpf,
                            request.password()
                    )
            );
            user = userDetailsService.loadUserByUsername(formattedCpf);

        String jwt = jwtService.generateToken(user);
        long expirationTime = jwtService.getExpirationTime();

        return new LoginResponseDTO(jwt, expirationTime);
    }
    public LoginResponseDTO authCustomer(CustomerAuthRequestDTO auth) {
        TableModel table=tableService.findTableByUuidOrThrow(auth.tableId());
        Optional<TabModel> openTab=tabService
                .findFirstByTableAndCustomerNameAndStatus(table,auth.name(), TabStatus.OPEN);

        CustomerModel customerToAuthenticate;

        if (openTab.isPresent()) {
            System.out.println("Comanda existente encontrada. Logando cliente...");
            customerToAuthenticate = openTab.get().getCustomer();
        } else {
            System.out.println("Nenhuma comanda aberta encontrada. Criando nova sessão...");

            CustomerModel tempCustomer = CustomerModel.builder()
                    .name(auth.name())
                    .identifyingId(null) // CPF será definido após a criação da comanda
                    .build();
            CustomerModel savedCustomer = customerRepository.save(tempCustomer);

            TabModel newTab = TabModel.builder()
                    .table(table)
                    .customer(savedCustomer)
                    .status(TabStatus.OPEN)
                    .totalAmount(BigDecimal.ZERO)
                    .build();
            TabModel savedTab = tabService.save(newTab);

            savedCustomer.setIdentifyingId(String.valueOf(savedTab.id));
            customerToAuthenticate = customerRepository.save(savedCustomer);
        }

        String jwt = jwtService.generateToken(customerToAuthenticate);
        long expirationTime = jwtService.getExpirationTime();

        return new LoginResponseDTO(jwt, expirationTime);

    }


}
