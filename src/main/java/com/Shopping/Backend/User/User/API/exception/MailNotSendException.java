package com.Shopping.Backend.User.User.API.exception;

public class MailNotSendException extends RuntimeException {
    public MailNotSendException(String message){
        super(message);
    }
}