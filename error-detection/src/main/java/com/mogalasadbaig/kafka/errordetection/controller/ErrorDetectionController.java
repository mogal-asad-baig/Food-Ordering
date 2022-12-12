package com.mogalasadbaig.kafka.errordetection.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mogalasadbaig.kafka.errordetection.entity.SuccessOrFailModel;
import com.mogalasadbaig.kafka.errordetection.service.StatusDetectionService;

@RestController
@RequestMapping("/check")
public class ErrorDetectionController {

	@Autowired
	private StatusDetectionService statusDetectionService;
	
	@GetMapping("/status")
	public SuccessOrFailModel checkStatus(@RequestParam String name) {
		return statusDetectionService.getStatus(name);
	}
	
	@GetMapping("/all")
	public List<SuccessOrFailModel> getAll()
	{
		return statusDetectionService.getAll();
	}
}
