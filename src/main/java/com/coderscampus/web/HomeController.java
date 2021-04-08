package com.coderscampus.web;

import java.lang.reflect.InvocationTargetException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import com.coderscampus.dto.HomeDto;
import com.coderscampus.repository.PreferenceRepository;
import com.coderscampus.responseObject.MarsRoverApiResponse;
import com.coderscampus.service.MarsRoverApiService;

//The object of this class is to handle the HTTP request 

//Need a Controller -> listen for incoming HTTP request/ intercept(coming from(service package)... and executes specific code

//@Controller -> Type of component visible to spring...Extends the @Component class(kinda)

@Controller
public class HomeController {
	
	@Autowired
	private MarsRoverApiService roverService;
	
	

	@GetMapping("/")
	public String getIndexPage (ModelMap model, Long userId, Boolean createUser) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		HomeDto homeDto = homeDtoDefaultSetting(userId);
		
		if(Boolean.TRUE.equals(createUser) && userId == null) {
			// insert a new row 
			homeDto = roverService.save(homeDto);
		} else {
			// query the database with the userId...getting a row from the db using userId(primary key)
			 homeDto = roverService.findByUserId(userId);	
			 if(homeDto == null) {
				 homeDto = homeDtoDefaultSetting(userId);
			 }
			
		}
		
	MarsRoverApiResponse roverData = roverService.getRoverData(homeDto);
	model.put("roverData", roverData);
	model.put("homeDto", homeDto);
	// only should be putting the valid cameras for the rover 
	model.put("validCamera", roverService.getValidCamera().get(homeDto.getMarsApiRoverData()));
	// if remember preferences is not checked 
	if(!Boolean.TRUE.equals(homeDto.getRememberPreferences()) && userId != null) {
		//blow away whats in the db for that row of user 
		HomeDto defaultHomeDto = homeDtoDefaultSetting(userId);
		roverService.save(defaultHomeDto);
	}
	return "index";
	
	
	}
	
	

	private HomeDto homeDtoDefaultSetting(Long userId) {
		HomeDto homeDto = new HomeDto();
		homeDto.setMarsApiRoverData("Opportunity");
		homeDto.setMarsSol(1);
		homeDto.setUserId(userId);
		return homeDto;
	}
	
	@PostMapping("/")
	public String postIndexView(HomeDto homeDto) {
		homeDto = roverService.save(homeDto);
		return "redirect:/?userId=" +homeDto.getUserId();
	}
}

	/*
	@PostMapping("/")
	public String postHomeView (ModelMap model, @RequestParam String marsApiRoverData ) {
		MarsRoverApiResponse roverData = roverService.getRoverData(marsApiRoverData);
		model.put("roverData", roverData);
		return "index";
		
	}
	*/


