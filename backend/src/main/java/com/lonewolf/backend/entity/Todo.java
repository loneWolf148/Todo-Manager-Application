package com.lonewolf.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "todos")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Todo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "description", nullable = false)
    @EqualsAndHashCode.Exclude
    private String description;

    @Column(name = "deadline", nullable = false)
    @EqualsAndHashCode.Exclude
    private LocalDate deadline;

    @Column(name = "completed", nullable = false)
    @JsonSetter("isCompleted")
    @EqualsAndHashCode.Exclude
    private boolean isCompleted;
}
