package com.lonewolf.backend.service;

import com.lonewolf.backend.config.qualifier.TerminalDateQualifier;
import com.lonewolf.backend.entity.Todo;
import com.lonewolf.backend.model.config.TerminalDate;
import com.lonewolf.backend.model.exception.TodoException;
import com.lonewolf.backend.model.response.TodoResponse;
import com.lonewolf.backend.repository.TodoRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TodoService {

    private final TodoRepository repository;
    private final TerminalDate terminalDate;

    @Autowired
    public TodoService(TodoRepository repository, @TerminalDateQualifier TerminalDate terminalDate) {
        this.repository = repository;
        this.terminalDate = terminalDate;
    }

    private LocalDate parseLocalFormattedDate(String terminal) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(terminalDate.getFormat());
        return LocalDate.parse(terminal, formatter);
    }

    public TodoResponse fetchAllTodos(String terminal) {
        LocalDate requestedDate = (terminal != null) ? parseLocalFormattedDate(terminal) : terminalDate.getDate();
        return new TodoResponse(requestedDate, repository.fetchAllTodos(requestedDate));
    }

    public Todo fetchTodo(int id, String terminal) {
        Todo todo = repository.findById(id).orElse(null);
        if (todo == null) {
            throw new TodoException("invalid todo id specified", HttpStatus.NOT_FOUND);
        }

        LocalDate requestedDate = (terminal != null) ? parseLocalFormattedDate(terminal) : terminalDate.getDate();
        if (todo.getDeadline().compareTo(requestedDate) < 0) {
            throw new TodoException("todo doesn't exist in current date scope", HttpStatus.BAD_REQUEST);
        }

        return todo;
    }

    private boolean todoExists(Todo todo) {
        List<Todo> existingTodos = repository.todoExists(todo.getDescription(), todo.getDeadline());
        return existingTodos != null && !existingTodos.isEmpty();
    }

    public boolean addTodo(@NonNull Todo newTodo) {
        boolean alreadyExists = todoExists(newTodo);
        if (alreadyExists) {
            throw new TodoException("duplicate todo spotted", HttpStatus.CONFLICT);
        }

        Todo addedTodo = repository.saveAndFlush(newTodo);
        if (addedTodo.getId() <= 0) {
            throw new TodoException("todo couldn't be added", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return true;
    }

    public boolean updateTodo(@NonNull Todo updatedTodo, String terminal) {
        Todo oldTodo = fetchTodo(updatedTodo.getId(), terminal);

        oldTodo.setDescription(updatedTodo.getDescription());
        oldTodo.setDeadline(updatedTodo.getDeadline());
        oldTodo.setCompleted(updatedTodo.isCompleted());

        Todo savedTodo = repository.saveAndFlush(oldTodo);
        if (savedTodo.getId() <= 0) {
            throw new TodoException("todo couldn't be updated", HttpStatus.CREATED);
        }
        return true;
    }

    public boolean deleteTodo(int id, String terminal) {
        Todo todo = fetchTodo(id, terminal);
        repository.delete(todo);

        return true;
    }
}