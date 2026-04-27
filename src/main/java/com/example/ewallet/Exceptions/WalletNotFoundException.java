package com.example.ewallet.Exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class WalletNotFoundException extends RuntimeException{

    public WalletNotFoundException(String message){
        super(message);
    }
}
