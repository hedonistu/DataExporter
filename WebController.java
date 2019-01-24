package com.springjpa.controller;

import java.util.Arrays;

import com.springjpa.model.ProjectRecord;
import com.springjpa.repo.ProjectRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springjpa.model.ProjectRecord;
import com.springjpa.repo.ProjectRecordRepository;

@RestController
public class WebController {
	@Autowired
	ProjectRecordRepository repository;
	
	@RequestMapping("/save")
	public String process(){
		// save a single Customer
		repository.save(new ProjectRecord("2018-04-01T00:00:00+02:00", "900","900","1",10));
		return "Done";
	}
	
	
	@RequestMapping("/findall")
	public String findAll(){
		String result = "";
		
		for(ProjectRecord cust : repository.findAll()){
			result += cust.toString() + "<br>";
		}
		
		return result;
	}

}

