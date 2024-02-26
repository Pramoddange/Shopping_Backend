package com.Shopping.Backend.User.User.API.exception;
public class UnAuthorized extends RuntimeException{
    public UnAuthorized(String message){
        super(message);
    }
}
