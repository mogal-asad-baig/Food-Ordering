package com.mogalasadbaig.kafka.restaurantowner.util;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mogalasadbaig.kafka.restaurantowner.entity.Restaurant;

public class RestaurantSerializer implements Serializer<Restaurant>{

	@Override
	public byte[] serialize(String topic, Restaurant restaurant) {
		 byte[] retVal = null;
		    ObjectMapper objectMapper = new ObjectMapper();
		    try {
		      retVal = objectMapper.writeValueAsString(restaurant).getBytes();
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		    return retVal;
	}

	
}
