package com.mogalasadbaig.kafka.users.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mogalasadbaig.kafka.users.entity.UserEntity;
import com.mogalasadbaig.kafka.users.model.OrderDetails;
import com.mogalasadbaig.kafka.users.repo.UserEntityRepo;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserEntityRepo repo;
	
	@Override
	public boolean createUser(UserEntity user) {
		
	  user.setOrder(new ArrayList<>());
	  UserEntity users = repo.save(user);
	  
	  if(users == null)
		  return false;
	  else
		  return true;
	  
	}

	@Override
	public List<UserEntity> getUsers() {
		return repo.findAll();
	}

	@Override
	public boolean placeOrder(OrderDetails order) {
		System.out.println("shcvshdvchsdvcsdcjhs");
		long phoneNumber = order.getPhoneNumber();
		System.out.println("sasvahvx");
		Optional<UserEntity> orderDetails= repo.findById(phoneNumber);
		if(orderDetails.isEmpty())
		{
			System.out.println("adsadhvasdvsadvhsvad");
		return false;
		}
		else
		{
			System.out.println("zsdavsdvasjdvsvbcdsbvdch");
			UserEntity userEntity = orderDetails.get();
			userEntity.getOrder().add(order);
			System.out.println(order);
			UserEntity user = repo.save(userEntity);
			System.out.println("vdshvd" + user.getOrder());
			System.out.println("order placed");
			return true;
		}
			
	}
	

}
