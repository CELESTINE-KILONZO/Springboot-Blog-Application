package com.example.blog.application.utils.exceptions;

public class CustomExceptionResponse extends RuntimeException {
    public CustomExceptionResponse(String message) {
        super(message);
    }
}
