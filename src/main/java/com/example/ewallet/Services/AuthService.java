package com.example.ewallet.Services;

import com.example.ewallet.PayLoads.RequestDTOs.LoginRequestDTO;
import com.example.ewallet.PayLoads.RequestDTOs.RegisterRequestDTO;
import com.example.ewallet.PayLoads.ResponseDTOs.LoginResponseDTO;
import com.example.ewallet.PayLoads.ResponseDTOs.RegisterResponseDTO;
import jakarta.validation.Valid;

public interface AuthService {

    RegisterResponseDTO register(@Valid RegisterRequestDTO request);
    LoginResponseDTO login(@Valid LoginRequestDTO request);
}
