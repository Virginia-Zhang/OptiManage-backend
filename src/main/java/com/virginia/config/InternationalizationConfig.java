package com.virginia.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

// Create a configuration class to configure the MessageSource Bean
@Configuration
public class InternationalizationConfig {
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        // Set the base name of the message resource file
        messageSource.setBasename("classpath:messages");
        // Set default encoding
        messageSource.setDefaultEncoding("UTF-8");
        // Set cache duration (seconds)
        messageSource.setCacheSeconds(3600);
        // Disable fallback to system language
        messageSource.setFallbackToSystemLocale(false);
        return messageSource;
    }
}
