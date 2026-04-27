package com.example.ewallet.Exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InsufficientBalanceException extends RuntimeException{

    public InsufficientBalanceException(String message){
        super(message);
    }
}
