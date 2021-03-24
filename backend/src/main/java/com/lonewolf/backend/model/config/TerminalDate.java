package com.lonewolf.backend.model.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class TerminalDate {

    private final String format;
    private final LocalDate date;
}
