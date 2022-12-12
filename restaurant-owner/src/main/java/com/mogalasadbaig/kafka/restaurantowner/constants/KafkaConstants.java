package com.mogalasadbaig.kafka.restaurantowner.constants;

public class KafkaConstants {

	public static String KAFKA_BROKERS = "localhost:9092";
	
	public static String CLIENT_ID = "client1";
	
	public static String TOPIC_NAME_RESTAURANT = "restaurant";
	
	public static String TOPIC_NAME_RESTAURANT_CONFIRM  = "restaurants-reg-status"; 
	
	public static int MAX_POLL_RECORDS = 100;
}
