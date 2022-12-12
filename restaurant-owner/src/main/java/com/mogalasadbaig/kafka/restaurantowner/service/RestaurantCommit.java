package com.mogalasadbaig.kafka.restaurantowner.service;

import java.util.List;
import java.util.Map;

import com.mogalasadbaig.kafka.restaurantowner.entity.Restaurant;
import com.mogalasadbaig.kafka.restaurantowner.exceptions.ItemNotFoundException;
import com.mogalasadbaig.kafka.restaurantowner.exceptions.RestaurantNotFoundException;
import com.mogalasadbaig.kafka.restaurantowner.model.Demographics;

public interface RestaurantCommit {

	
	public void commitRestuarant(Restaurant restaurant);
	
	public boolean addMenuItems(Map<String, Boolean> items, Demographics demographics) throws RestaurantNotFoundException;
	
	public boolean changeRestaurantAvailability(Demographics demographics) throws RestaurantNotFoundException;
	
	public boolean changeItemAvailability(Demographics demographics, String item) throws RestaurantNotFoundException, ItemNotFoundException;
	
	public List<Restaurant> getAllRestaurants();
	
	public Restaurant getByDemographics(Demographics demographics) throws RestaurantNotFoundException;

	//public List<Restaurant> getRestaurantByNameAndCityAndStreet(String name, String city, String street);
	
}
