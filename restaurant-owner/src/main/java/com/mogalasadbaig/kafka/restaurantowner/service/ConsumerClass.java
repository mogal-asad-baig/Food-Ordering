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
		    Consumer<String, Restaurant> consumer = createConsumer();
		
			while(true) {
//		    TopicPartition partition  = new TopicPartition(KafkaConstants.TOPIC_NAME_RESTAURANT,0);
//			consumer.seek(partition, 4);
			ConsumerRecords<String,Restaurant> consumerRecords = consumer.poll(Duration.ofMillis(1000));
					
			consumerRecords.forEach(record->
			{
				restaurantCommit.commitRestuarant(record.value());
//				 System.out.println(record.value().getPhone_number());
				
			});
					
			consumer.commitAsync();
			
			}
		
//		consumer.close();
	}
	
}
