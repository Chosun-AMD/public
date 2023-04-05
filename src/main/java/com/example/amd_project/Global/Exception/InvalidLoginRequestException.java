package com.example.amd_project.Global.Exception;

public class InvalidLoginRequestException extends RuntimeException{
    private static final String MESSAGE = "INVALID LOGIN ID, PASSWORD";
    public InvalidLoginRequestException() {
        super(MESSAGE);
    }
}
