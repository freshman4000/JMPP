package com.freshman4000.utility;

import org.springframework.stereotype.Component;

@Component
public class CustomException extends Exception {

    public CustomException() {
    }

    public CustomException(String message) {
        super(message);
    }
}