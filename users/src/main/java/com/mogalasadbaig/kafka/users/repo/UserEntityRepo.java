package com.mogalasadbaig.kafka.users.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mogalasadbaig.kafka.users.entity.UserEntity;

public interface UserEntityRepo extends MongoRepository<UserEntity, Long>{

}
