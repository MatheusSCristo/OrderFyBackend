package com.orderfy.auth_service.Auth;

import com.orderfy.auth_service.Auth.dto.AuthRequest;
import com.orderfy.auth_service.Auth.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try{
        AuthResponse response= authService.login(request);
            return ResponseEntity.ok(response);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }




}