package com.Shopping.Backend.User.User.API;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(info = @Info(
		title = "User API Specification",
		version = "1.0",
		description = "this swagger page contain all end point of user API",
		contact = @Contact(
		name="Pramod Dange",
		email = "pramodsdange1@gmail.com"
		)
))
@SpringBootApplication
public class UserApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApiApplication.class, args);
	}

}
