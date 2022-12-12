package com.mogalasadbaig.kafka.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTopicDeamonConsumer extends Thread{

	@Autowired
	private ConsumerClass consumer;
	public void run()
	{
		consumer.runConsumer();
	}
}
