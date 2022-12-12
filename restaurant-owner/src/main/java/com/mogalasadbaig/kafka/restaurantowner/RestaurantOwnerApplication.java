package com.mogalasadbaig.kafka.restaurantowner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mogalasadbaig.kafka.restaurantowner.service.RestaurantTopicDeamonConsumer;

@SpringBootApplication
public class RestaurantOwnerApplication implements CommandLineRunner{

	@Autowired
	private RestaurantTopicDeamonConsumer deamon;
	
	public static void main(String[] args) {
	    
		SpringApplication.run(RestaurantOwnerApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		deamon.start();
	}
	
	

}
