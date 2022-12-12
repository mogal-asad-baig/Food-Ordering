package com.mogalasadbaig.kafka.errordetection.service;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mogalasadbaig.kafka.constants.KafkaConstants;
import com.mogalasadbaig.kafka.errordetection.entity.SuccessOrFailModel;
import com.mogalasadbaig.kafka.errordetection.repo.StatusTableRepo;
import com.mogalasadbaig.kafka.errordetection.util.SuccessOrFailModelDeserializer;

@Service
public class RegistrationStatusTopicConsumer {
	
	@Autowired
	private StatusTableRepo statusTableRepo;

	private static Consumer<String, SuccessOrFailModel> consumer = createConsumer();

	public static Consumer<String, SuccessOrFailModel> createConsumer()
	{
		Properties props = new Properties();
		
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.KAFKA_BROKERS);
		props.put(ConsumerConfig.GROUP_ID_CONFIG,"consumerGroup3");
		props.put(ConsumerConfig.CLIENT_ID_CONFIG, KafkaConstants.CLIENT_ID);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, SuccessOrFailModelDeserializer.class.getName());
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, KafkaConstants.MAX_POLL_RECORDS);
		Consumer<String, SuccessOrFailModel> consumer =  new KafkaConsumer<>(props);
		consumer.subscribe(Collections.singletonList(KafkaConstants.TOPIC_NAME_RESTAURANT_CONFIRM));
		return consumer;
		
	}
	
	public void runConsumer()
	{
		
			while(true) {

			ConsumerRecords<String,SuccessOrFailModel> consumerRecords = consumer.poll(Duration.ofMillis(1000));
					
			consumerRecords.forEach(record->
			{
				statusTableRepo.save(record.value());
				
			});
					
			consumer.commitAsync(); // this one commits to __consumer_offsets topic which comes by default i guess
			
			}
		
//		consumer.close();
	}
	
}
