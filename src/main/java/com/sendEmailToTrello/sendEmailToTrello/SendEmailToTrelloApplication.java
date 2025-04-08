package com.sendEmailToTrello.sendEmailToTrello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SendEmailToTrelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(SendEmailToTrelloApplication.class, args);
	}

}
