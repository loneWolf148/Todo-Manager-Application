package com.lonewolf.backend.config.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Getter;

@Getter
public class DeveloperConfig {

    @JacksonXmlProperty(localName = "age", isAttribute = true)
    private int age;

    @JacksonXmlProperty(localName = "location", isAttribute = true)
    private String location;

    @JacksonXmlText
    private String name;
}