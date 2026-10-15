package com.uday.novapay.service;
import com.uday.novapay.dto.request.LoginRequest;
import com.uday.novapay.dto.response.LoginResponse;
import com.uday.novapay.entity.User;
import com.uday.novapay.exception.EmailAlreadyExistsException;
import com.uday.novapay.exception.InvalidCredentialsException;
import com.uday.novapay.mapper.UserMapper;
import com.uday.novapay.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.uday.novapay.dto.request.RegisterRequest;
import com.uday.novapay.dto.response.RegisterResponse;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final WalletService walletService ;

    public AuthService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, JwtService jwtService, WalletService walletService) {
        this.userRepository = userRepository;
        this.userMapper =userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.walletService= walletService;
    }
        public RegisterResponse register(RegisterRequest request) {
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new EmailAlreadyExistsException("Email Already Exists");

            }
            User user =userMapper.toEntity(request);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User savedUser = userRepository.save(user);
            walletService.createWallet(savedUser);

            return RegisterResponse.builder()
                    .id(savedUser.getId())
                    .fullName(savedUser.getFullName())
                    .email(savedUser.getEmail())
                    .phoneNumber(savedUser.getPhoneNumber())
                    .message("Registration Successful").build();

    }
public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()->new InvalidCredentialsException("Email or Password is Invalid "));

        if(!passwordEncoder.matches (request.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException("Invalid Email or password");}
          String token =  jwtService.generateToken(user.getEmail());

return LoginResponse.builder()
        .id(user.getId())
        .fullName(user.getFullName())
        .email(user.getEmail())
        .token(token)
        .message("Login Successful").build();
}}

