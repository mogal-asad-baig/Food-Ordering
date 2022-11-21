package com.mogalasadbaig.kafka.restaurantowner.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mogalasadbaig.kafka.restaurantowner.entity.Restaurant;

public interface RestaurantRepo extends MongoRepository<Restaurant, String>{


	public List<Restaurant> findByName(String name);

}
