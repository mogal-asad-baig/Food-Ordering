package com.mogalasadbaig.kafka.users.util;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mogalasadbaig.kafka.users.model.OrderDetails;

public class OrderDetailsSerializer implements Serializer<OrderDetails>{

	@Override
	public byte[] serialize(String topic, OrderDetails data) {
		byte[] retVal = null;
	    ObjectMapper objectMapper = new ObjectMapper();
	    try {
	      retVal = objectMapper.writeValueAsString(data).getBytes();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return retVal;
	}

}
