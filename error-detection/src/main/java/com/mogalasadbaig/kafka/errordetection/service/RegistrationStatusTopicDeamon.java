package com.mogalasadbaig.kafka.errordetection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationStatusTopicDeamon extends Thread{

	@Autowired
	private RegistrationStatusTopicConsumer status;
	public void run()
	{
		status.runConsumer();
	}
}
