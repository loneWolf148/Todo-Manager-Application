package com.lonewolf.backend.controller;

import com.lonewolf.backend.entity.Todo;
import com.lonewolf.backend.model.exception.TodoException;
import com.lonewolf.backend.model.response.TodoResponse;
import com.lonewolf.backend.service.TodoService;
import com.lonewolf.backend.util.ResponseGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin
public class TodoController {

    private final TodoService service;

    @Autowired
    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping
    public TodoResponse fetchAllTodos(@RequestParam(required = false) String terminal) {
        return service.fetchAllTodos(terminal);
    }

    @GetMapping("/{id}")
    public Todo fetchTodo(@PathVariable int id, @RequestParam(required = false) String terminal) {
        return service.fetchTodo(id, terminal);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> addTodo(@RequestBody Todo newTodo) {
        boolean isAdded = service.addTodo(newTodo);
        if (!isAdded) {
            throw new TodoException("todo couldn't be added", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseGenerator.generateMessageResponse("todo added successfully", HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Map<String, String>> updateTodo(@RequestBody Todo updatedTodo,
                                                          @RequestParam(required = false) String terminal) {
        boolean isUpdated = service.updateTodo(updatedTodo, terminal);
        if (!isUpdated) {
            throw new TodoException("todo couldn't be updated", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseGenerator.generateMessageResponse("todo updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, String>> deleteTodo(@PathVariable int id,
                                                          @RequestParam(required = false) String terminal) {
        boolean isDeleted = service.deleteTodo(id, terminal);
        if (!isDeleted) {
            throw new TodoException("todo couldn't be deleted", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseGenerator.generateMessageResponse("todo deleted successfully", HttpStatus.OK);
    }
}