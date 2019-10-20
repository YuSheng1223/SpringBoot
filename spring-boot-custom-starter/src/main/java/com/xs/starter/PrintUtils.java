package com.xs.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrintUtils {

    private static final Logger logger = LoggerFactory.getLogger(PrintUtils.class);

    private final String text;


    public PrintUtils(String text){
        this.text = text;
    }
    public void printText(){
        logger.info(" starter print text : {} ",text);
    }


}
