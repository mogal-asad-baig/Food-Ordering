package com.mogalasadbaig.kafka.users.util;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mogalasadbaig.kafka.users.entity.UserEntity;

public class UserEntitySerializer implements Serializer<UserEntity>{

	@Override
	public byte[] serialize(String topic, UserEntity user) {
		 byte[] retVal = null;
		    ObjectMapper objectMapper = new ObjectMapper();
		    try {
		      retVal = objectMapper.writeValueAsString(user).getBytes();
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		    return retVal;
	}

	
}
