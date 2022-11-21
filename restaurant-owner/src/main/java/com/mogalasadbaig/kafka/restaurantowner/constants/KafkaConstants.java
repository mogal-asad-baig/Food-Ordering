package com.mogalasadbaig.kafka.restaurantowner.constants;

public class KafkaConstants {

	public static String KAFKA_BROKERS = "localhost:9092";
	
	public static String CLIENT_ID = "client1";
	
	public static String TOPIC_NAME_RESTAURANT = "restaurants";
	
	public static String TOPIC_NAME_RESTAURANT_CONFIRM  = "restaurant-confirm"; 
	
	public static int MAX_POLL_RECORDS = 100;
}
