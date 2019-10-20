package com.xs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootCustomStarter {
    public SpringBootCustomStarter() {
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCustomStarter.class, args);
    }
}
