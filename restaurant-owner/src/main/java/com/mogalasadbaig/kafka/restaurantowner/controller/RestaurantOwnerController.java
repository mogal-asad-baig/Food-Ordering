package com.mogalasadbaig.kafka.restaurantowner.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mogalasadbaig.kafka.restaurantowner.entity.Restaurant;
import com.mogalasadbaig.kafka.restaurantowner.exceptions.GenericException;
import com.mogalasadbaig.kafka.restaurantowner.exceptions.ItemNotFoundException;
import com.mogalasadbaig.kafka.restaurantowner.exceptions.RestaurantNotFoundException;
import com.mogalasadbaig.kafka.restaurantowner.service.ProducerClass;
import com.mogalasadbaig.kafka.restaurantowner.service.RestaurantCommit;

@RestController
@RequestMapping("/restaurant")
public class RestaurantOwnerController {

	@Autowired
	private ProducerClass producer;
	
	@Autowired
	private RestaurantCommit restaurantCommit;
	
	@PostMapping("/create")
	public long createNewRestaurant(@RequestBody Restaurant restaurant) throws GenericException
	{
		 long offset = producer.runProducer(restaurant);
		 
		 if(offset == -1)
		 {
			 throw new GenericException("Something went wrong try again");
		 }
		 
	     return offset;
		
	}
	
	// we can add caching here
	@GetMapping("/getrestaurants")
	public List<Restaurant> getAllRestaurants()
	{
		return restaurantCommit.getAllRestaurants();
	}
//	
//	@GetMapping("/getrestaurant-by-name-and-city")
//	public List<Restaurant> getRestaurantByNameAndCity(@RequestParam String name, @RequestParam String city)
//	{
//	    return restaurantCommit.getRestaurantByNameAndCity(name,city);
//	}
	
	//spring security can be used here
	@PostMapping("/add-menu-items")
	public boolean addMenuItems(@RequestParam String name, @RequestParam String city, @RequestBody Map<String, Boolean> menu) throws RestaurantNotFoundException
	{
		
		return restaurantCommit.addMenuItems(menu,name);
	}
	
	
	@PostMapping("/change-restaurant-availability")
	public boolean changeRestaurantAvailability(@RequestParam String name) throws RestaurantNotFoundException
	{
		return restaurantCommit.changeRestaurantAvailability(name);
	}
	
	
	@PostMapping("/change-item-avialability")
	public boolean changeItemAvailability(@RequestParam String name, @RequestParam String item) throws ItemNotFoundException, RestaurantNotFoundException
	{
		return restaurantCommit.changeItemAvailability(name, item);
	}
	
	@PostMapping("/verify")
	public boolean verifyRestaurant(@RequestParam String name)
	{
		return true;
	}
	
}
