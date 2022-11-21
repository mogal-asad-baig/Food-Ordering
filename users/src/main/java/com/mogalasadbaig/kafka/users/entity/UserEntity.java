package com.mogalasadbaig.kafka.users.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mogalasadbaig.kafka.users.model.OrderDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "userentity")
public class UserEntity {

	
	@Id
	private long phoneNumber;
	
	private String name;
	
	private List<OrderDetails> order;
	
	
	
	

}
