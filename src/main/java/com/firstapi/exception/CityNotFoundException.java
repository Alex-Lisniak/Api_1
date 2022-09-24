package com.firstapi.exception;

public class CityNotFoundException extends RuntimeException{

    public CityNotFoundException(String message){
        super(message);
    }
}
