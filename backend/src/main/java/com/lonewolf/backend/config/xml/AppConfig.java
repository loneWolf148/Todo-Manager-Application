package com.lonewolf.backend.config.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;

@JacksonXmlRootElement(localName = "configuration")
@Getter
public class AppConfig {

    @JacksonXmlProperty(localName = "terminal-date")
    private TerminalDateConfig terminalDateConfig;

    @JacksonXmlProperty(localName = "developer")
    private DeveloperConfig developerConfig;
}