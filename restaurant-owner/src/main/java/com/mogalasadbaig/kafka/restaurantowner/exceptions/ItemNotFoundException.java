package com.mogalasadbaig.kafka.restaurantowner.exceptions;

public class ItemNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ItemNotFoundException(String message)
	{
		super(message);
	}

}
