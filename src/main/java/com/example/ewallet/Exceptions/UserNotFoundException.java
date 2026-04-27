package com.example.ewallet.Exceptions;

import com.example.ewallet.Models.User;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message){
        super(message);
    }
}
