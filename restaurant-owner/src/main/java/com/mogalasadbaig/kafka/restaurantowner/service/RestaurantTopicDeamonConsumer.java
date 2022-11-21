package com.mogalasadbaig.kafka.restaurantowner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantTopicDeamonConsumer extends Thread{
   
	@Autowired
	private ConsumerClass consumer;
	
	public void run()
	{
		System.out.println("object "+consumer);
	    consumer.runConsumer();	
	}
	
	
}
