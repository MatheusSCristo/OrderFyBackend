package com.orderfy.auth_service.Auth;

import com.orderfy.auth_service.Auth.config.JwtService;
import com.orderfy.auth_service.Auth.dto.AuthRequest;
import com.orderfy.auth_service.Auth.dto.AuthResponse;
import com.orderfy.auth_service.Employee.Employee;
import com.orderfy.auth_service.Employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Employee employee = employeeRepository
                .findByCpfAndRestaurantId(request.getCpf(), request.getRestaurantId())
                .orElse(null);

        if (employee == null) {
            return ResponseEntity.status(401).body("Credenciais inválidas ou usuário não existe neste restaurante.");
        }

        if (!passwordEncoder.matches(request.getPassword(), employee.getPassword())) {
            return ResponseEntity.status(401).body("Credenciais inválidas.");
        }

        String token = jwtService.generateTokenForStaff(employee);
        return ResponseEntity.ok(new AuthResponse(token));
    }




}