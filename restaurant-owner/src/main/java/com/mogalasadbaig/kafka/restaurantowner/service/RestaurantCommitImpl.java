package com.mogalasadbaig.kafka.restaurantowner.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mogalasadbaig.kafka.restaurantowner.entity.Restaurant;
import com.mogalasadbaig.kafka.restaurantowner.exceptions.ItemNotFoundException;
import com.mogalasadbaig.kafka.restaurantowner.exceptions.RestaurantNotFoundException;
import com.mogalasadbaig.kafka.restaurantowner.model.Demographics;
import com.mogalasadbaig.kafka.restaurantowner.model.SuccessOrFailModel;
import com.mogalasadbaig.kafka.restaurantowner.repo.RestaurantRepo;

@Service
public class RestaurantCommitImpl implements RestaurantCommit {

	@Autowired
	private RestaurantRepo restaurantRepo;
	@Autowired
	private ProducerClassRestaurantConfirm restaurantConfirm;

	@Override
	public void commitRestuarant(Restaurant restaurant) {
		
		System.out.println("asdasdjgajhdsg");
		if(restaurant.getDemographics().getName() == null || restaurant.getDemographics().getCity() == null || restaurant.getDemographics().getStreet() == null || restaurant.getPhone_number() == null || restaurant.getDemographics().getState() == null)
		{
			System.out.println("adjasdkjhak");
			//restaurantConfirm.runProducer(new SuccessOrFailModel(restaurant.getName(),"FAIL", "Required Data is missing"));
			return;
		}

		
			System.out.println("skcksbdwdefhkwd");
			restaurant.setAvailability("OPEN");
			restaurant.setMenu(new HashMap<>());
			Restaurant res = restaurantRepo.save(restaurant);
            if(res == null)
            {
            	//restaurantConfirm.runProducer(new SuccessOrFailModel(restaurant.getName(),"FAIL", "Something Went wrong"));
            	return;
            }
            else {
			
			System.out.println("sdcjfwevdgwedgwedgwdgw");
			restaurantConfirm.runProducer(new SuccessOrFailModel(restaurant.getDemographics(),"SUCCESS", "Restaurant Successfully created"));
            }
		
		
	
	}

	@Override
	public boolean addMenuItems(Map<String, Boolean> items, Demographics demographics) throws RestaurantNotFoundException {

		
	//List<Restaurant> restaurants = this.getRestaurantByNameAndCityAndStreet(name, city, street);
		Optional<Restaurant> res = restaurantRepo.findById(demographics);
		if (res.isEmpty()) //restaurants.size() == 0
		{
			throw new RestaurantNotFoundException("Add restaurant first");
		} else {
			Map<String, Boolean> item = res.get().getMenu();
			for (Entry<String, Boolean> entry : items.entrySet()) {
				item.put(entry.getKey(), entry.getValue());
			}
			restaurantRepo.save(res.get());
			return true;
		}

	}

	@Override
	public boolean changeRestaurantAvailability(Demographics demographics) throws RestaurantNotFoundException {
		Optional<Restaurant> res = restaurantRepo.findById(demographics);

		if (res.isEmpty()) {
			throw new RestaurantNotFoundException("Add restaurant first");
		} else {
			Restaurant restaurant = res.get();
			if (restaurant.getAvailability().equals("OPEN"))
				restaurant.setAvailability("CLOSED");
			
			else
				res.get().setAvailability("OPEN");
			
			restaurantRepo.save(restaurant);

			return true;
		}

	}

	@Override
	public boolean changeItemAvailability(Demographics demographics, String item) throws RestaurantNotFoundException, ItemNotFoundException {

		Optional<Restaurant> res = restaurantRepo.findById(demographics);

		if (res.isEmpty()) {
			throw new RestaurantNotFoundException("Add restaurant first");
		}
		else
		{
			Restaurant restaurant = res.get();
			if(restaurant.getMenu().containsKey(item))
			{
				if(restaurant.getMenu().get(item) == false)
					restaurant.getMenu().put(item, true);
				else
					restaurant.getMenu().put(item, false);
				
				restaurantRepo.save(restaurant);
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

	@Override
	public Restaurant getByDemographics(Demographics demographics) throws RestaurantNotFoundException {
		Optional<Restaurant> restaurant = restaurantRepo.findById(demographics);
		if(restaurant.isEmpty())
			throw new RestaurantNotFoundException("Add restaurant first");
		return restaurant.get();
	}


}
