package com.idfc.aop.logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExampleController {

	@Autowired
	ExampleService exampleService;
	
    @GetMapping("/test")
    public String testControllerMethod() {
        return "Controller method executed!";
    }
    
    @GetMapping("/service")
    public String testService() {
        //return "Controller method executed!";
    	return exampleService.executeServiceMethod();
    }
    
}
