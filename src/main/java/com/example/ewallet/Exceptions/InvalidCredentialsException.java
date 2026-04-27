package com.example.ewallet.Exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidCredentialsException extends RuntimeException{

    public InvalidCredentialsException(String message){
        super(message);

    }
}
