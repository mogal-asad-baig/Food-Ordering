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
import com.mogalasadbaig.kafka.restaurantowner.model.SuccessOrFailModel;
import com.mogalasadbaig.kafka.restaurantowner.util.SuccessOrFailModelSerializer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProducerClassRestaurantConfirm {

	private static Producer<String, SuccessOrFailModel> producer = createProducer();
	
	public static Producer<String, SuccessOrFailModel> createProducer()
	{
		Properties props = new Properties();
		
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.KAFKA_BROKERS);
		props.put(ProducerConfig.CLIENT_ID_CONFIG, "client8");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, SuccessOrFailModelSerializer.class.getName());
		
		return new KafkaProducer<>(props);
	}
	
	public long runProducer(SuccessOrFailModel sof)
	{
		
		ProducerRecord<String, SuccessOrFailModel> record = new ProducerRecord<>(KafkaConstants.TOPIC_NAME_RESTAURANT_CONFIRM,sof);
		RecordMetadata metadata = null;
		try {
			System.out.println("ajdhajdvavdavdhgvasdvavda");
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
