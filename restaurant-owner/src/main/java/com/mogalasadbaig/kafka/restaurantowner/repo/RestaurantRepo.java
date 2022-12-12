package com.mogalasadbaig.kafka.restaurantowner.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mogalasadbaig.kafka.restaurantowner.entity.Restaurant;
import com.mogalasadbaig.kafka.restaurantowner.model.Demographics;

public interface RestaurantRepo extends MongoRepository<Restaurant, Demographics>{

//	@Query("{name : ?0}")
//	public List<Restaurant> findBy(String name);
//	
//	public List<Restaurant> findByCity(String city);
//	
//	public List<Restaurant> findByStreet(String street);
	
	

}
