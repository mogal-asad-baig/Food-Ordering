package com.mogalasadbaig.kafka.errordetection.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mogalasadbaig.kafka.errordetection.entity.SuccessOrFailModel;
import com.mogalasadbaig.kafka.errordetection.repo.StatusTableRepo;

@Service
public class StatusDetectionServiceImpl implements StatusDetectionService {

	@Autowired
	private StatusTableRepo statusTableRepo;
	
	@Override
	public SuccessOrFailModel getStatus(String name) {
		
		int reval = 0;
		
		
		while(reval <= 4)
		{
		
		Optional<SuccessOrFailModel> optional = statusTableRepo.findById(name);
		 if(optional.isEmpty())
		 {
			 System.out.println("sdvsadjvs");
			 reval++;
			 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		 }
		 else
		 {
			 System.out.println("sfsgdjgsdfgsjhgf");
			 return optional.get();
		 }
		 
		}
		
		return null;
		
	}

	@Override
	public List<SuccessOrFailModel> getAll() {
		return statusTableRepo.findAll();
		
	}

}
