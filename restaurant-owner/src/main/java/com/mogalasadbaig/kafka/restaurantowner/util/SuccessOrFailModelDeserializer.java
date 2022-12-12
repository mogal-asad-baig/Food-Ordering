package com.mogalasadbaig.kafka.restaurantowner.util;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mogalasadbaig.kafka.restaurantowner.model.SuccessOrFailModel;

public class SuccessOrFailModelDeserializer implements Deserializer<SuccessOrFailModel>{

	@Override
	public SuccessOrFailModel deserialize(String topic, byte[] data) {

		ObjectMapper mapper = new ObjectMapper();
	    SuccessOrFailModel sof = null;
	    try {
	       sof = mapper.readValue(data, SuccessOrFailModel.class);
	    } catch (Exception e) {

	      e.printStackTrace();
	    }
	    return sof;
	}

}
