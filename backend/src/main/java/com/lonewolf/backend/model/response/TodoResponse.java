package com.lonewolf.backend.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lonewolf.backend.entity.Todo;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TodoResponse {

    @JsonProperty("terminal")
    private final LocalDate terminalDate;

    private final List<Todo> items;
}