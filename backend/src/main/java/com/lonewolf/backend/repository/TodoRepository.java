package com.lonewolf.backend.repository;

import com.lonewolf.backend.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

    @Query("FROM Todo t WHERE t.deadline >= ?1 ORDER BY t.deadline")
    List<Todo> fetchAllTodos(LocalDate terminalDate);

    @Query("FROM Todo t WHERE lower(t.description)=lower(?1) AND t.deadline=?2")
    List<Todo> todoExists(String description, LocalDate deadline);
}