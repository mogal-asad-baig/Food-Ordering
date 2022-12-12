package com.mogalasadbaig.kafka.errordetection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mogalasadbaig.kafka.errordetection.service.RegistrationStatusTopicDeamon;

@SpringBootApplication
public class ErrorDetectionApplication implements CommandLineRunner{

	@Autowired
	private RegistrationStatusTopicDeamon deamon;
	
	public static void main(String[] args) {
		SpringApplication.run(ErrorDetectionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		deamon.start();
	}

}
