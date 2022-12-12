package com.mogalasadbaig.kafka.errordetection.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mogalasadbaig.kafka.errordetection.entity.SuccessOrFailModel;

@Repository
public interface StatusTableRepo extends MongoRepository<SuccessOrFailModel, String>{

}
