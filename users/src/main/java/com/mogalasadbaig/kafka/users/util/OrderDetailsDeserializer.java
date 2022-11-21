package com.mogalasadbaig.kafka.users.util;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mogalasadbaig.kafka.users.model.OrderDetails;

public class OrderDetailsDeserializer implements Deserializer<OrderDetails>{

	@Override
	public OrderDetails deserialize(String topic, byte[] data) {
		
		ObjectMapper mapper = new ObjectMapper();
		   OrderDetails order = null;
		    try {
		      order = mapper.readValue(data, OrderDetails.class);
		    } catch (Exception e) {

		      e.printStackTrace();
		    }
		    return order;
		  }
	}

	

