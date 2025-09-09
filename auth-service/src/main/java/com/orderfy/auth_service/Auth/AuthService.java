package com.orderfy.auth_service.Auth;

import com.orderfy.auth_service.Auth.config.JwtService;
import com.orderfy.auth_service.Auth.dto.AuthRequest;
import com.orderfy.auth_service.Auth.dto.AuthResponse;
import com.orderfy.auth_service.Auth.dto.CustomerSessionRequestDTO;
import com.orderfy.auth_service.Auth.dto.CustomerSessionResponseDTO;
import com.orderfy.auth_service.Employee.Employee;
import com.orderfy.auth_service.Employee.EmployeeRepository;
import com.orderfy.auth_service.Restaurant.RestaurantService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final EmployeeRepository employeeRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final RestaurantService restaurantService;

    public AuthResponse login(AuthRequest request ) throws CredentialException {
        Employee employee = employeeRepository
                .findByCpfAndRestaurantId(request.getCpf(), request.getRestaurantId())
                .orElse(null);

        if (employee == null) {
            throw new EntityNotFoundException("Funcionário não encontrado");
        }

        if (!passwordEncoder.matches(request.getPassword(), employee.getPassword())) {
            throw new CredentialException("Credenciais incorretas");
        }

        String token= jwtService.generateTokenForStaff(employee);
        return new AuthResponse(token,employee.getName(),employee.getRestaurant().getId(),employee.getCpf());
    }

    public CustomerSessionResponseDTO createGuestSession(CustomerSessionRequestDTO request) {
        restaurantService.findById(request.restaurantId());

        UUID sessionId = UUID.randomUUID();
        UUID restaurantUuid = UUID.fromString(request.restaurantId());
        UUID tableUuid = UUID.fromString(request.tableId());

        String token = jwtService.generateTokenForGuest(restaurantUuid, tableUuid, sessionId);

        return new CustomerSessionResponseDTO(token,request.clientName(), sessionId.toString());
    }
}
