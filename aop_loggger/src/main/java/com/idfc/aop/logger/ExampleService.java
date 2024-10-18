package com.idfc.aop.logger;

import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    public String executeServiceMethod() {
        return "Service method executed!";
    }
}
