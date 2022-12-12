package com.mogalasadbaig.kafka.users.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {

	private long phoneNumber;
	
	private String restaurantName;
	
	private String city;
	
	private List<Order> items;
}
