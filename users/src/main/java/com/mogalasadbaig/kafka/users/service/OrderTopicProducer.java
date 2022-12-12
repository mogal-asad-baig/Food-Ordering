package com.mogalasadbaig.kafka.users.service;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;

import com.mogalasadbaig.kafka.users.kafkaconstants.KafkaConstants;
import com.mogalasadbaig.kafka.users.model.OrderDetails;
import com.mogalasadbaig.kafka.users.util.OrderDetailsSerializer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderTopicProducer {

	public static Producer<String, OrderDetails> createProducer()
	{
		Properties props = new Properties();
		
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.KAFKA_BROKERS);
		props.put(ProducerConfig.CLIENT_ID_CONFIG, KafkaConstants.CLIENT_ID);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, OrderDetailsSerializer.class.getName());
		
		return new KafkaProducer<>(props);
	}
	
	public long runProducer(OrderDetails order)
	{
		System.out.println("Produver started");
		Producer<String, OrderDetails> producer = createProducer();
		ProducerRecord<String, OrderDetails> record = new ProducerRecord<>("order-details", order);
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
