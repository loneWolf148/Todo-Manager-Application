package com.lonewolf.backend.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseGenerator {

    private static final String Message = "message";

    private ResponseGenerator() {
    }

    public static ResponseEntity<Map<String, String>> generateMessageResponse(String message, HttpStatus status) {
        Map<String, String> body = new HashMap<>();
        body.put(Message, message);
        return new ResponseEntity<>(body, status);
    }
}
