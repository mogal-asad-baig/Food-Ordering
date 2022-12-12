package com.mogalasadbaig.kafka.restaurantowner.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mogalasadbaig.kafka.restaurantowner.entity.Restaurant;
import com.mogalasadbaig.kafka.restaurantowner.exceptions.CheckDataException;
import com.mogalasadbaig.kafka.restaurantowner.exceptions.GenericException;
import com.mogalasadbaig.kafka.restaurantowner.exceptions.ItemNotFoundException;
import com.mogalasadbaig.kafka.restaurantowner.exceptions.RestaurantNotFoundException;
import com.mogalasadbaig.kafka.restaurantowner.model.Demographics;
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
	public long createNewRestaurant(@RequestBody @Validated Restaurant restaurant, BindingResult bindingResult) throws GenericException, CheckDataException
	{

		 long offset = producer.runProducer(restaurant);
		 
		 if(offset == -1)
		 {
			 throw new GenericException("Something went wrong try again");
		 }
		 
	     return offset;
		
	}
	
	@GetMapping("/getrestaurants")
	public List<Restaurant> getAllRestaurants()
	{
		return restaurantCommit.getAllRestaurants();
	}
	
	@GetMapping("/get-by-demographics")
	public Restaurant getByDemographics(@RequestParam String name, @RequestParam String city, @RequestParam String street, @RequestParam String state) throws RestaurantNotFoundException
	{
	    return restaurantCommit.getByDemographics(new Demographics(name,city,street,state));
	}
	
	@PostMapping("/add-menu-items")
	public boolean addMenuItems(@RequestParam String name, @RequestParam String city, @RequestParam String street, @RequestParam String state, @RequestBody Map<String, Boolean> menu) throws RestaurantNotFoundException
	{
		
		return restaurantCommit.addMenuItems(menu,new Demographics(name,city,street,state));
	}
	
	
	@PostMapping("/change-restaurant-availability")
	public boolean changeRestaurantAvailability(@RequestParam String name, @RequestParam String city, @RequestParam String street, @RequestParam String state) throws RestaurantNotFoundException
	{
		return restaurantCommit.changeRestaurantAvailability(new Demographics(name,city,street,state));
	}
	
	
	@PostMapping("/change-item-avialability")
	public boolean changeItemAvailability(@RequestParam String name, @RequestParam String city, @RequestParam String street, @RequestParam String state, @RequestParam String item) throws ItemNotFoundException, RestaurantNotFoundException
	{
		return restaurantCommit.changeItemAvailability(new Demographics(name,city,street,state), item);
	}
	
	@PostMapping("/verify")
	public boolean verifyRestaurant(@RequestBody Demographics demographics)
	{
		return true;
	}
	

	
}
