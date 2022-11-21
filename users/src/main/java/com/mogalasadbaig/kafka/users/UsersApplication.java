package com.mogalasadbaig.kafka.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mogalasadbaig.kafka.users.service.OrderTopicDeamonConsumer;
import com.mogalasadbaig.kafka.users.service.UserTopicDeamonConsumer;


@SpringBootApplication
public class UsersApplication implements CommandLineRunner{

	
	@Autowired
	private UserTopicDeamonConsumer deamon;
	@Autowired
	private OrderTopicDeamonConsumer deamon1;
	
	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		deamon.start();
		deamon1.start();
	}

}
