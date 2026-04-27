package com.example.ewallet.Services;

import com.example.ewallet.PayLoads.RequestDTOs.LoginRequestDTO;
import com.example.ewallet.PayLoads.RequestDTOs.RegisterRequestDTO;
import com.example.ewallet.PayLoads.ResponseDTOs.AuthResponseDTO;
import jakarta.validation.Valid;

public interface AuthService {

    AuthResponseDTO register(@Valid RegisterRequestDTO request);
    AuthResponseDTO login(@Valid LoginRequestDTO request);
}
