package com.mogalasadbaig.kafka.restaurantowner.service;

import java.util.List;
import java.util.Map;

import com.mogalasadbaig.kafka.restaurantowner.entity.Restaurant;
import com.mogalasadbaig.kafka.restaurantowner.exceptions.ItemNotFoundException;
import com.mogalasadbaig.kafka.restaurantowner.exceptions.RestaurantNotFoundException;

public interface RestaurantCommit {

	
	public boolean commitRestuarant(Restaurant restaurant);
	
	public boolean addMenuItems(Map<String, Boolean> items, String name) throws RestaurantNotFoundException;
	
	public boolean changeRestaurantAvailability(String name) throws RestaurantNotFoundException;
	
	public boolean changeItemAvailability(String name, String item) throws RestaurantNotFoundException, ItemNotFoundException;
	public List<Restaurant> getAllRestaurants();
//	
//	public List<Restaurant> getRestaurantByNameAndCity(String name, String city);
	
}
