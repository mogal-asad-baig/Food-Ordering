package com.mogalasadbaig.kafka.restaurantowner.service;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;

import com.mogalasadbaig.kafka.restaurantowner.constants.KafkaConstants;
import com.mogalasadbaig.kafka.restaurantowner.entity.Restaurant;
import com.mogalasadbaig.kafka.restaurantowner.util.RestaurantSerializer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProducerClass {


//     public static void main(String args[])
//     {
//    	 Restaurant obj = new Restaurant();
//    	 obj.setName("abcd res7560754");
//    	 obj.setCity("rayachoty");
//    	 obj.setState("Andhrapradesh");
//    	 obj.setStreet("alimabad street");
//    	 obj.setPhone_number("00909090909");
//    	 obj.setAvailability("open");
//    	 
//    	 runProducer(obj);
//    	 
//     }
	// this can be modified to receive parameters and set the values accordingly instead of hardcoded values
	public static Producer<String, Restaurant> createProducer()
	{
		Properties props = new Properties();
		
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.KAFKA_BROKERS);
		props.put(ProducerConfig.CLIENT_ID_CONFIG, KafkaConstants.CLIENT_ID);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, RestaurantSerializer.class.getName());
		
		return new KafkaProducer<>(props);
	}
	
	public long runProducer(Restaurant restaurant)
	{
		Producer<String, Restaurant> producer = createProducer();
		ProducerRecord<String, Restaurant> record = new ProducerRecord<>(KafkaConstants.TOPIC_NAME_RESTAURANT,restaurant);
		RecordMetadata metadata = null;
		try {
			metadata = producer.send(record).get();
			System.out.println(metadata.offset());
			log.info("offset is"+metadata.offset());
		}
		catch(ExecutionException e)
		{
		   	e.printStackTrace();
		   	return -1;
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
			return -1;
		}
		
		return metadata.offset();
	}
}
