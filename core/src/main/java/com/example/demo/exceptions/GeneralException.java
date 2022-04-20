package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;

public class GeneralException extends Exception {
    private final String code;

    public GeneralException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
