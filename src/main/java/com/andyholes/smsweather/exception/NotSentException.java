package com.andyholes.smsweather.exception;

public class NotSentException extends RuntimeException{
    public NotSentException(String errorMessage){
        super(errorMessage);
    }
}
