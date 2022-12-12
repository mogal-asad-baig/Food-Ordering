package com.mogalasadbaig.kafka.restaurantowner.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuccessOrFailModel {

	private Demographics demographics;
	
	private String status;
	
	private String message;
}
