package com.example.ewallet.Exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class JwtAuthException extends RuntimeException{

    public JwtAuthException(String message){
        super(message);
    }
}
