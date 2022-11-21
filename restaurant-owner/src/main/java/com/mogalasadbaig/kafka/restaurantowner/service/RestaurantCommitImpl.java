package com.mogalasadbaig.kafka.restaurantowner.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mogalasadbaig.kafka.restaurantowner.entity.Restaurant;
import com.mogalasadbaig.kafka.restaurantowner.exceptions.ItemNotFoundException;
import com.mogalasadbaig.kafka.restaurantowner.exceptions.RestaurantNotFoundException;
import com.mogalasadbaig.kafka.restaurantowner.repo.RestaurantRepo;

@Service
public class RestaurantCommitImpl implements RestaurantCommit {

	@Autowired
	private RestaurantRepo restaurantRepo;
	@Autowired
	private ProducerClassRestaurantConfirm restaurantConfirm;

	@Override
	public boolean commitRestuarant(Restaurant restaurant) {

		Restaurant res = restaurantRepo.save(restaurant);
		if (res == null)
			return false;
		else
		{
			restaurantConfirm.runProducer(restaurant);
			return true;
		}
	}

	@Override
	public boolean addMenuItems(Map<String, Boolean> items, String name) throws RestaurantNotFoundException {

		Optional<Restaurant> res = restaurantRepo.findById(name);
		if (res.isEmpty()) {
			throw new RestaurantNotFoundException("Add restaurant first");
		} else {
			Map<String, Boolean> item = res.get().getMenu();
			for (Entry<String, Boolean> entry : items.entrySet()) {
				item.put(entry.getKey(), entry.getValue());
			}

			return true;
		}

	}

	@Override
	public boolean changeRestaurantAvailability(String name) throws RestaurantNotFoundException {
		Optional<Restaurant> res = restaurantRepo.findById(name);

		if (res.isEmpty()) {
			throw new RestaurantNotFoundException("Add restaurant first");
		} else {

			if (res.get().getAvailability().equals("OPEN"))
				res.get().setAvailability("CLOSED");
			else
				res.get().setAvailability("OPEN");

			return true;
		}

	}

	@Override
	public boolean changeItemAvailability(String name, String item) throws RestaurantNotFoundException, ItemNotFoundException {

		Optional<Restaurant> res = restaurantRepo.findById(name);

		if (res.isEmpty()) {
			throw new RestaurantNotFoundException("Add restaurant first");
		}
		else
		{
			if(res.get().getMenu().containsKey(item))
			{
				if(res.get().getMenu().get(item) == false)
					res.get().getMenu().put(item, true);
				else
					res.get().getMenu().put(item, false);
				return true;
			}
			else
				throw new ItemNotFoundException("Item Not Found");
		}
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		return restaurantRepo.findAll();
	}
//
//	@Override
//	public List<Restaurant> getRestaurantByNameAndCity(String name, String city) {
//		List<Restaurant> byName = restaurantRepo.findByName(name);
//		
//		List<Restaurant> res = byName.stream().filter(Restaurant -> Restaurant.getCity().equals(city)).collect(Collectors.toList());		
//		
//		return res;
//		
//	}

}
