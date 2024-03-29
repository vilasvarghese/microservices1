
package com.vilas.sl4j;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Sl4jApplication {

    // SLF4J's logging instance for this class
    // We could have used LoggerFactory.getLogger(Slf4jSpringBootApplication.class) as well
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
     
    // This is what SLF4J uses to bind to a specific logging implementation
    final StaticLoggerBinder binder = StaticLoggerBinder.getSingleton();
     
    @Autowired
    private HelloWorldService helloWorldService;
 
    public void run(Class<Sl4jApplication> c, String[] args) {
    	System.out.println("inside run");
        System.out.println("Vilas Logger Factory "+binder.getLoggerFactory());
        System.out.println("Vilas Logger Factory Class Str "+binder.getLoggerFactoryClassStr());
         
        LOGGER.debug(this.helloWorldService.getHelloMessage());
        if (args.length > 0 ) {
            LOGGER.error("Exit Code encountered", new Exception());
        }
    }
 
    public static void main(String[] args) throws Exception {
    	System.out.println("inside main");
    	SpringApplication.run(Sl4jApplication.class, args);
    }	
	
}
