package com.Shopping.Backend.User.User.API.exception;

public class UnavailableException extends RuntimeException{
    public UnavailableException (String message){
        super(message);
    }
}
