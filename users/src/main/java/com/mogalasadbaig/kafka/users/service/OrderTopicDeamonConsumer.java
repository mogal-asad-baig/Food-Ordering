package com.mogalasadbaig.kafka.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderTopicDeamonConsumer extends Thread{

	@Autowired
	private OrderTopicConsumer consumer;
	
	public void run()
	{
		System.out.println("sjdjasshjd");
		consumer.runConsumer();
	}
}
