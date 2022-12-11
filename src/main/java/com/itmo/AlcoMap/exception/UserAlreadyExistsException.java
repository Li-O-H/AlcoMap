package com.itmo.AlcoMap.exception;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException() {
        super("Such user already exists");
    }
}
