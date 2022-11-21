package com.mogalasadbaig.kafka.restaurantowner.exceptions;

public class RestaurantNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public RestaurantNotFoundException(String message)
	{
		super(message);
	}
}
