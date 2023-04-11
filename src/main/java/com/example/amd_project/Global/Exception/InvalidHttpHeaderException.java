package com.example.amd_project.Global.Exception;

public class InvalidHttpHeaderException extends RuntimeException{
    public InvalidHttpHeaderException(String message){
        super(message);
    }
}
