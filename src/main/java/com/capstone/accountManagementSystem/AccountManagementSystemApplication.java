package com.capstone.accountManagementSystem;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class AccountManagementSystemApplication {

	public static void main(String[] args) {
		System.out.println("check");
		SpringApplication.run(AccountManagementSystemApplication.class, args);
	}

	@Bean
    public Docket swaggerConfiguration() {        
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.capstone"))
                .build()
                .apiInfo(apiDetails());
    }    
    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Account Management System",
                "Account API",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact("PB2E Team","http://PB2E.io","PB2E.com"),
                "API License",
                "http://PB2E.io",
                Collections.emptyList());
    }
}
