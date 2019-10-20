package com.xs.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "print")
public class PrintProperties {


    private String text;

    public void setText(String text){
        this.text = text;
    }

    public String getText(){
        return this.text;
    }
}
