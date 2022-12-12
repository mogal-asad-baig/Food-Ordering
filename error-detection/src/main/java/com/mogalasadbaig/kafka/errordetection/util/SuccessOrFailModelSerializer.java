package com.mogalasadbaig.kafka.errordetection.util;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mogalasadbaig.kafka.errordetection.entity.SuccessOrFailModel;

public class SuccessOrFailModelSerializer implements Serializer<SuccessOrFailModel>{

	@Override
	public byte[] serialize(String topic, SuccessOrFailModel data) {
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
