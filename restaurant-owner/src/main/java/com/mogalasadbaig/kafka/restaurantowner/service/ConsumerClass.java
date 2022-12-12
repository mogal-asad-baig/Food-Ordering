package com.mogalasadbaig.kafka.restaurantowner.service;

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

import com.mogalasadbaig.kafka.restaurantowner.constants.KafkaConstants;
import com.mogalasadbaig.kafka.restaurantowner.entity.Restaurant;
import com.mogalasadbaig.kafka.restaurantowner.util.RestaurantDeserializer;

@Service
public class ConsumerClass {
	
	@Autowired
	private RestaurantCommit restaurantCommit;
	
	private static Consumer<String, Restaurant> consumer = createConsumer();
	
//	public static void main(String args[])
//	{
//		runConsumer();
//	}

	public static Consumer<String, Restaurant> createConsumer()
	{
		Properties props = new Properties();
		
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.KAFKA_BROKERS);
		props.put(ConsumerConfig.GROUP_ID_CONFIG,"consumerGroup1");
		props.put(ConsumerConfig.CLIENT_ID_CONFIG, KafkaConstants.CLIENT_ID);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, RestaurantDeserializer.class.getName());
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, KafkaConstants.MAX_POLL_RECORDS);
		Consumer<String, Restaurant> consumer =  new KafkaConsumer<>(props);
		consumer.subscribe(Collections.singletonList(KafkaConstants.TOPIC_NAME_RESTAURANT));
		return consumer;
		
	}
	
	public void runConsumer()
	{
		
			while(true) {

			ConsumerRecords<String,Restaurant> consumerRecords = consumer.poll(Duration.ofMillis(1000));
					
			consumerRecords.forEach(record->
			{
				restaurantCommit.commitRestuarant(record.value());
				
			});
					
			consumer.commitAsync(); // this one commits to __consumer_offsets topic which comes by default i guess
			
			}
		
	}
	
}
