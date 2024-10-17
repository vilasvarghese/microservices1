package com.idfcbank.savingaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SavingaccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(SavingaccountApplication.class, args);
		/*ConfigurableApplicationContext context = 
		Account myAccount = context.getBean(Account.class);
		System.out.println(myAccount);
		Account myAccount1 = context.getBean(Account.class);
		System.out.println(myAccount1);*/
		
	}

}
