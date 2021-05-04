package com.hsbg.scheduler;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class SchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulerApplication.class, args);
	}
	/*
	 * Option 1
	@Scheduled(fixedDelay = 2000)
	public void someMethod() {//this should be a void method which takes no parameters
		System.out.println("Time now "+new Date());
	}
 	In this case, the duration between the end of the last execution and the start of the next execution is fixed. The task always waits until the previous one is finished.
	This option should be used when it’s mandatory that the previous execution is completed before running again.
*/

	
	/*
	 * Option 2
	@Scheduled(fixedRate = 2000)
	public void someMethod() {//this should be a void method which takes no parameters
		System.out.println("Time now "+new Date());
	}
	
	Execute a task at a fixed interval of time.
	However, two tasks doesn't run in parallel
 */
	
	
	/*
	 * Option 3
	 *  Schedule a Task With Initial Delay
	
	@Scheduled(fixedDelay = 1000, initialDelay = 1000)
	public void scheduleFixedRateWithInitialDelayTask() {
		System.out.println("Time now "+new Date());
	}
	 */
 
/*
 * Option 4
 * cron expression to control the schedule of our tasks:
 
	@Scheduled(cron = "0 15 10 15 * ?")
	public void scheduleTaskUsingCronExpression() {
		System.out.println("Time now "+new Date());
	}
 */

	/**************************************************************
	 * There are more options: Refer https://www.baeldung.com/spring-scheduled-tasks
	 **************************************************************/
	
	
}
/*
 * Option 5
If we want to support parallel behavior in scheduled tasks, we need to add the @Async annotation:
@EnableAsync
public class ScheduledFixedRateExample {
    @Async
    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTaskAsync() throws InterruptedException {
        System.out.println(
          "Fixed rate task async - " + System.currentTimeMillis() / 1000);
        Thread.sleep(2000);
    }

}

*/
@Configuration
class SchedulerConfiguration{
	
}


/*
 * Create spring Boot web application.
 * Add @EnableScheduling in applicaiton
 * Create a SchedulerConfiguration class
 * Annotate it as @Configuration
 * Create a new void - no parameter method.
 * Annotate it as @Scheduled
 * 	check the options inside @Scheduled by tabbing
 * 
 * 

 *  
 *  */