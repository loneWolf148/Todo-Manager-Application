package com.lonewolf.backend.config;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.lonewolf.backend.BackendApplication;
import com.lonewolf.backend.config.qualifier.AppConfigQualifier;
import com.lonewolf.backend.config.qualifier.DeveloperQualifier;
import com.lonewolf.backend.config.qualifier.TerminalDateQualifier;
import com.lonewolf.backend.config.xml.AppConfig;
import com.lonewolf.backend.model.config.Developer;
import com.lonewolf.backend.model.config.TerminalDate;
import com.lonewolf.backend.model.exception.TodoException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class AppBeanContainer {

    private static final String Config_File = "config.xml";

    @Bean
    @AppConfigQualifier
    public AppConfig appConfigBean() {
        try {
            InputStream inputStream = BackendApplication.class.getClassLoader().getResourceAsStream(Config_File);
            XMLInputFactory inputFactory = XMLInputFactory.newFactory();
            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

            XmlMapper mapper = new XmlMapper();
            return mapper.readValue(reader, AppConfig.class);
        } catch (Exception exception) {
            throw new TodoException("Configuration XML Couldn't be parsed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Bean
    @DeveloperQualifier
    public Developer developerBean(@AppConfigQualifier AppConfig appConfig) {
        String devName = appConfig.getDeveloperConfig().getName();
        int devAge = appConfig.getDeveloperConfig().getAge();
        String devLocation = appConfig.getDeveloperConfig().getLocation();

        return new Developer(devName, devAge, devLocation);
    }

    @Bean
    @TerminalDateQualifier
    public TerminalDate terminalDateBean(@AppConfigQualifier AppConfig appConfig) {
        String format = appConfig.getTerminalDateConfig().getFormat();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate date = LocalDate.parse(appConfig.getTerminalDateConfig().getDate(), formatter);

        return new TerminalDate(format, date);
    }
}