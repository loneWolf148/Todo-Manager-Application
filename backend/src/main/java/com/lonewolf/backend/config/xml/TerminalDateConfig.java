package com.lonewolf.backend.config.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Getter;

@Getter
public class TerminalDateConfig {

    @JacksonXmlProperty(localName = "format", isAttribute = true)
    private String format;

    @JacksonXmlText
    private String date;
}