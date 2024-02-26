package com.Shopping.Backend.User.User.API.exception;
public class UserDoesNotExistException extends RuntimeException{
    public UserDoesNotExistException(String message){
        super(message);
    }
}
