package com.mogalasadbaig.kafka.restaurantowner.util;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mogalasadbaig.kafka.restaurantowner.entity.Restaurant;

public class RestaurantDeserializer implements Deserializer<Restaurant>{

	@Override
	public Restaurant deserialize(String topic, byte[] data) {
		ObjectMapper mapper = new ObjectMapper();
	    Restaurant restaurant = null;
	    try {
	      restaurant = mapper.readValue(data, Restaurant.class);
	    } catch (Exception e) {

	      e.printStackTrace();
	    }
	    return restaurant;
	  }
	

}
