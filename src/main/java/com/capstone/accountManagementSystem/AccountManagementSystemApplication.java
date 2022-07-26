package com.capstone.accountManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AccountManagementSystemApplication {

	public static void main(String[] args) {
		System.out.println("check");
		SpringApplication.run(AccountManagementSystemApplication.class, args);
	}

}
