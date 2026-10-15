package com.uday.novapay.controller;
import com.uday.novapay.dto.request.LoginRequest;
import com.uday.novapay.dto.request.RegisterRequest;
import com.uday.novapay.dto.response.LoginResponse;
import com.uday.novapay.dto.response.RegisterResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.uday.novapay.service.AuthService;


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


