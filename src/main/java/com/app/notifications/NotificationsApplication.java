package com.app.notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //to enable scheduled mails
public class NotificationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationsApplication.class, args);
	}

}
