package com.lonewolf.backend.model.exception;

import com.lonewolf.backend.util.StringExtensions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class TodoExceptionHandler {

    private static final String Message = "message";

    @ExceptionHandler({TodoException.class})
    public ResponseEntity<Map<String, String>> handleTodoException(TodoException exception) {
        Map<String, String> response = new HashMap<>();
        response.put(Message, StringExtensions.capitalize(exception.getMessage()));

        return new ResponseEntity<>(response, exception.getErrorStatus());
    }
}