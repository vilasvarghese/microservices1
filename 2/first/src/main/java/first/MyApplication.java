package first;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MyApplication {

	public static void main(String[] args) {
		System.out.println("Hello World!!");
		ConfigurableApplicationContext context = SpringApplication.run(MyApplication.class, args);
		Employee emp = 	context.getBean(Employee.class);
		emp.print();
	}
}