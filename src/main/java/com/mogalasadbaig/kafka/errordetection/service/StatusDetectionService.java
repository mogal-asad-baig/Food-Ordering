package com.mogalasadbaig.kafka.errordetection.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mogalasadbaig.kafka.errordetection.entity.SuccessOrFailModel;

@Service
public interface StatusDetectionService {

	public SuccessOrFailModel getStatus(String name);
	
	
	public List<SuccessOrFailModel> getAll();

}
