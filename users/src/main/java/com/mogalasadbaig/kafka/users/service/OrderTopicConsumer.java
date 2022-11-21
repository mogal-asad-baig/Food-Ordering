package com.mogalasadbaig.kafka.users.service;

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

import com.mogalasadbaig.kafka.users.kafkaconstants.KafkaConstants;
import com.mogalasadbaig.kafka.users.model.OrderDetails;
import com.mogalasadbaig.kafka.users.util.OrderDetailsDeserializer;

@Service
public class OrderTopicConsumer {

	@Autowired
	private UserService userService;
	
	public static Consumer<String, OrderDetails> createConsumer()
	{
		Properties props = new Properties();
		
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.KAFKA_BROKERS);
		props.put(ConsumerConfig.GROUP_ID_CONFIG,"consumerGroup3");
		props.put(ConsumerConfig.CLIENT_ID_CONFIG, KafkaConstants.CLIENT_ID);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, OrderDetailsDeserializer.class.getName());
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, KafkaConstants.MAX_POLL_RECORDS);
		Consumer<String, OrderDetails> consumer =  new KafkaConsumer<>(props);
		consumer.subscribe(Collections.singletonList("order-details"));
		return consumer;
		
	}
	
	public void runConsumer()
	{
		System.out.println("order topic consmer");
		    Consumer<String, OrderDetails> consumer = createConsumer();
		
			while(true) {
//		    TopicPartition partition  = new TopicPartition(KafkaConstants.TOPIC_NAME_UserEntity,0);
//			consumer.seek(partition, 4);
			ConsumerRecords<String,OrderDetails> consumerRecords = consumer.poll(Duration.ofMillis(1000));
					
			consumerRecords.forEach(record->
			{
				
				userService.placeOrder(record.value());
//				 System.out.println(record.value().getPhone_number());
				
			});
					
			consumer.commitAsync();
			
			}
		
//		consumer.close();
	}
	
}
