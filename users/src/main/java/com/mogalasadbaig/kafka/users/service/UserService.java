package com.mogalasadbaig.kafka.users.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mogalasadbaig.kafka.users.entity.UserEntity;
import com.mogalasadbaig.kafka.users.model.OrderDetails;

@Repository
public interface UserService {

	public boolean createUser(UserEntity user);
	
	public List<UserEntity> getUsers();
	
	public boolean placeOrder(OrderDetails order);
}
