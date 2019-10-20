package com.xs.starter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({PrintProperties.class})
public class PrintAutoConfiguration {

    @Autowired
    private PrintProperties printProperties;

    @Bean
    @ConditionalOnProperty(prefix = "print.config", name = "enable", havingValue = "true")
    public PrintUtils InitializePrintUtils(){
        return new PrintUtils(this.printProperties.getText());
    }


}
