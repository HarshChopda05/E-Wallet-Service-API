package com.example.ewallet.Services.Implementations;

import com.example.ewallet.Exceptions.UserAlreadyExistsException;
import com.example.ewallet.Exceptions.UserNotFoundException;
import com.example.ewallet.Models.Type.CurrencyType;
import com.example.ewallet.Models.User;
import com.example.ewallet.Models.Wallet;
import com.example.ewallet.PayLoads.RequestDTOs.LoginRequestDTO;
import com.example.ewallet.PayLoads.RequestDTOs.RegisterRequestDTO;
import com.example.ewallet.PayLoads.ResponseDTOs.LoginResponseDTO;
import com.example.ewallet.PayLoads.ResponseDTOs.RegisterResponseDTO;
import com.example.ewallet.Repositories.UserRepository;
import com.example.ewallet.Repositories.WalletRepository;
import com.example.ewallet.Security.JwtService;
import com.example.ewallet.Services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public RegisterResponseDTO register(RegisterRequestDTO request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already registered");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .build();

        user = userRepository.save(user);

        Wallet wallet = Wallet.builder()
                .balance(BigDecimal.ZERO)
                .currency(request.getInitialCurrency() != null
                        ? request.getInitialCurrency()
                        : CurrencyType.INR)
                .user(user)
                .build();

        walletRepository.save(wallet);


        String token = jwtService.generateToken(user);

        return RegisterResponseDTO.builder()
                .message("User registered successfully")
                .build();
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new UserNotFoundException("User Not Found!"));

        String token = jwtService.generateToken(user);

        return LoginResponseDTO.builder()
                .token(token)
                .message("Login successful")
                .build();
    }
}

