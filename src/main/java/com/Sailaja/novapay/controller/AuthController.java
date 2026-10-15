package com.Sailaja.novapay.controller;
import com.Sailaja .novapay.dto.request.LoginRequest;
import com.Sailaja .novapay.dto.request.RegisterRequest;
import com.Sailaja .novapay.dto.response.LoginResponse;
import com.Sailaja .novapay.dto.response.RegisterResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.Sailaja .novapay.service.AuthService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
public RegisterResponse register(@Valid @RequestBody RegisterRequest registerRequest){
    return authService.register(registerRequest);
    }
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }
}


