package com.mogalasadbaig.kafka.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mogalasadbaig.kafka.users.entity.UserEntity;
import com.mogalasadbaig.kafka.users.exception.GenericException;
import com.mogalasadbaig.kafka.users.model.OrderDetails;
import com.mogalasadbaig.kafka.users.service.OrderTopicProducer;
import com.mogalasadbaig.kafka.users.service.ProducerClass;
import com.mogalasadbaig.kafka.users.service.UserService;

@RestController
@RequestMapping("/users")
public class UsersController {

   @Autowired
   private ProducerClass producer;
   
   @Autowired
   private OrderTopicProducer orderTopicProducer;
   
   @Autowired
   private UserService userService;
	
	@PostMapping("/create")
	public long createUser(@RequestBody UserEntity user) throws GenericException
	{
		long offset = producer.runProducer(user);
		
		if(offset == -1)
		{
			throw new GenericException("Some thing went wrong try again");
		}
		else
		{
			return offset;
		}
	}
	
	
	@GetMapping("/getusers")
	public List<UserEntity> getUsers()
	{
		return userService.getUsers();
	}
	
	
	@PostMapping("/place-order")
	public long placeOrder(@RequestBody OrderDetails order)
	{
		return orderTopicProducer.runProducer(order);
	}
}
