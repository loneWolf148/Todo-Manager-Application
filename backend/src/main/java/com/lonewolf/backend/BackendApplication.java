package com.lonewolf.backend;

import com.lonewolf.backend.model.config.Developer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Slf4j
public class BackendApplication {

    private final Developer developer;

    @Autowired
    public BackendApplication(Developer developer) {
        this.developer = developer;
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @PostConstruct
    public void appInitialized() {
        log.info("Developer is {} years old {} living in {}", developer.getAge(), developer.getName(), developer.getLocation());
    }
}