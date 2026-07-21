package com.example.ewallet.Controllers;

import com.example.ewallet.PayLoads.RequestDTOs.LoginRequestDTO;
import com.example.ewallet.PayLoads.RequestDTOs.RegisterRequestDTO;
import com.example.ewallet.PayLoads.ResponseDTOs.LoginResponseDTO;
import com.example.ewallet.PayLoads.ResponseDTOs.RegisterResponseDTO;
import com.example.ewallet.Services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Authentication APIs", description = "User registration and login operations")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    //Register User
    @Operation(summary = "User Register", description = "Create a new User account in the E-Wallet system")
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request){
        RegisterResponseDTO response = authService.register(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //Login User
    @Operation(summary = "User Login", description = "Authenticates user credentials and returns a JWT token")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        LoginResponseDTO response = authService.login(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}