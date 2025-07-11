package com.kushagra.journalApp.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "quote.api")
@Getter
@Setter
public class QuoteApiConfig {
    private String key;
}
