package com.example.amd_project.Global.Exception;

import java.io.IOException;

public class InvalidLoginRequestException extends RuntimeException {
    private static final String MESSAGE = "INVALID LOGIN ID, PASSWORD";
    public InvalidLoginRequestException() throws IOException {
        super(MESSAGE);
    }
}
