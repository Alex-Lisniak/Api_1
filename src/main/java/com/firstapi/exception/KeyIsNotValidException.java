package com.firstapi.exception;

public class KeyIsNotValidException extends RuntimeException{

    public KeyIsNotValidException(String message){
        super(message);
    }
}
