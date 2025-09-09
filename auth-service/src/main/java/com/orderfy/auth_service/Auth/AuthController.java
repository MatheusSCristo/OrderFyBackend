package com.orderfy.auth_service.Auth;

import com.orderfy.auth_service.Auth.dto.AuthRequest;
import com.orderfy.auth_service.Auth.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) throws CredentialException {
        AuthResponse response= authService.login(request);
        return ResponseEntity.ok(response);
    }




}