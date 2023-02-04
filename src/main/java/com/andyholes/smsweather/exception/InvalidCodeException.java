package com.andyholes.smsweather.exception;

public class InvalidCodeException extends RuntimeException{
    public InvalidCodeException(String errorMessage){
        super(errorMessage);
    }
}

