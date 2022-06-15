package com.microservice.app.service;


/**
 * Exception class for email already exists
 */
public class EmailAlreadyTakenException extends Throwable{
    public EmailAlreadyTakenException(String message){
        super(message);
    }
}
