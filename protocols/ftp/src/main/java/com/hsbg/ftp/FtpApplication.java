package com.hsbg.ftp;

import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hsbg.ftp.FtpConfiguration.Gate;

@SpringBootApplication(scanBasePackages={
"com.hsbg.ftp", "com.github.kuljaninemir"})
public class FtpApplication {

	public static void main(String[] args) {
		SpringApplication.run(FtpApplication.class, args);
	}

    @Bean
    public ApplicationRunner runner(Gate gate) {
        return args -> {
            List list = gate.list(".");
            System.out.println("Result:" + list);
        };
    }
}
