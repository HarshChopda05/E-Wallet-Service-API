package com.example.ewallet.PayLoads.ResponseDTOs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LoginResponseDTO {

    private String message;

    private String token;
}
