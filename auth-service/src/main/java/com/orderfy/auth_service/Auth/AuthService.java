package com.orderfy.auth_service.Auth;

import com.orderfy.auth_service.Auth.config.JwtService;
import com.orderfy.auth_service.Auth.dto.AuthRequest;
import com.orderfy.auth_service.Employee.Employee;
import com.orderfy.auth_service.Employee.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final EmployeeRepository employeeRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public String login(AuthRequest request ) throws CredentialException {
        Employee employee = employeeRepository
                .findByCpfAndRestaurantId(request.getCpf(), request.getRestaurantId())
                .orElse(null);

        if (employee == null) {
            throw new EntityNotFoundException("Funcionário não encontrado");
        }

        if (!passwordEncoder.matches(request.getPassword(), employee.getPassword())) {
            throw new CredentialException("Senha incorreta");
        }

        return jwtService.generateTokenForStaff(employee);
    }


}
