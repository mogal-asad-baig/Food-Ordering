package com.mogalasadbaig.kafka.users.util;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mogalasadbaig.kafka.users.entity.UserEntity;

public class UserEnitityDeserializer implements Deserializer<UserEntity>{

	@Override
	public UserEntity deserialize(String topic, byte[] data) {
		ObjectMapper mapper = new ObjectMapper();
	   UserEntity user = null;
	    try {
	      user = mapper.readValue(data, UserEntity.class);
	    } catch (Exception e) {

	      e.printStackTrace();
	    }
	    return user;
	  }
	

}
