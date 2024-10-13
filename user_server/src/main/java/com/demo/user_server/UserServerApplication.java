package com.demo.user_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		info = @Info(
				title = "MS REST User API Documentation",
				description = """
						MS 1.O Developement in Spring 3.1.0 With Microservices,
						Modules Are - User Module
						""",
				version = "MS 1.O"
		),
		externalDocs = @ExternalDocumentation(
				description = "My Self",
				url = "https://www.myself.net/"
		)
)

@SpringBootApplication
@EnableDiscoveryClient
public class UserServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServerApplication.class, args);
	}

}
