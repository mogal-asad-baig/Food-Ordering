package com.mogalasadbaig.kafka.errordetection.entity;






import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mogalasadbaig.kafka.model.Demographics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("status-collection")
public class SuccessOrFailModel {

	@Id
	private Demographics demographics;
	
	private String status;
	
	private String message;
}
