package com.vilas.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LogApplication {

    private static final Logger LOGGER = LogManager.getLogger(LogApplication.class);
    
    public static void main(String[] args)
    {
        ApplicationContext ctx = SpringApplication.run(LogApplication.class, args);
         
        LOGGER.info("Info level log message");
        LOGGER.debug("Debug level log message");
        LOGGER.error("Error level log message");
    }

}
