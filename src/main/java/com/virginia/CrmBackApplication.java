package com.virginia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableAsync
@EnableMethodSecurity
public class CrmBackApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrmBackApplication.class, args);
    }

}
