package com.Dhiraj.eventManagemetApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EventManagemetAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventManagemetAppApplication.class, args);
		System.out.println("Started the Event App");
	}

}
