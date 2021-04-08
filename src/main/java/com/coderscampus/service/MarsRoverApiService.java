package com.coderscampus.service;



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.coderscampus.dto.HomeDto;
import com.coderscampus.repository.PreferenceRepository;
import com.coderscampus.responseObject.MarsPhoto;
import com.coderscampus.responseObject.MarsRoverApiResponse;





@Service
//This class is capturing the API data with a GET request --> Controller.java class is listening for the endpoint
public class MarsRoverApiService {
	
	
	private static final String API_KEY = "lKd0pbRZEw98su4iYHOP1acIdz89vufFESoWk1B1";
	private Map<String,List<String>> validCamera = new HashMap<>();
	
	@Autowired
	private PreferenceRepository preferencesRepo;

	
	//Each rover is specfic to camera -> create constructor 
	public MarsRoverApiService() {
		validCamera.put("Opportunity", Arrays.asList("FHAZ","RHAZ","NAVCAM","PANCAM","MINITES"));
		validCamera.put("Curiosity", Arrays.asList("FHAZ","RHAZ","MAST","CHEMCAM","MAHLI","MARDI","NAVCAM"));
		validCamera.put("Spirit", Arrays.asList("FHAZ","RHAZ","NAVCAM","PANCAM","MINITES"));

	}
	
	
	/* we want to be able to add the functionally the different roverType user can choose 
	 * and correspond it with the photos of that rover and the specific day 
	 * 
	 *  going to call the getRoverData() in my controller class

	 */
	
	
	public MarsRoverApiResponse getRoverData(HomeDto homeDto) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		RestTemplate rt = new RestTemplate();

		List<String> apiUrlEnpoints = getApiUrlEnpoints(homeDto);
		List<MarsPhoto> photos = new ArrayList<>();
		MarsRoverApiResponse response = new MarsRoverApiResponse();

		apiUrlEnpoints.stream()
		.forEach(url -> {
		MarsRoverApiResponse apiResponse = rt.getForObject(url, MarsRoverApiResponse.class);
		photos.addAll(apiResponse.getPhotos());
		});

		response.setPhotos(photos);

		return response;
		}

	
		// method for building of list of url objects that we are going to have to call for the different cameras
		// Objective method: grab getCamera method in homeDto...then build a API URL to call in order to fetch pictures for rover/camera/sol
		public List<String> getApiUrlEnpoints (HomeDto homeDto) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<String> url = new ArrayList<>();
		// grab the method in the homeDto class that has the getters for the camera
		Method[] method = homeDto.getClass().getMethods();
		// loop through the methods and grab the getCamera() for each camera
		for (Method i : method) {
			if(i.getName().indexOf("getCamera") > -1 &&  Boolean.TRUE.equals(i.invoke(homeDto)) ) {
				// storing the specfic camera name
				String cameraName = i.getName().split("getCamera")[1].toUpperCase();
				if(validCamera.get(homeDto.getMarsApiRoverData()).contains(cameraName)) {
					url.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" +API_KEY + "&camera=" + cameraName);
				}
				
			}
		}
		return url;
	}


		public Map<String, List<String>> getValidCamera() {
			return validCamera;
		}

		/*
		 * saving the data to the database
		 */

		public HomeDto save(HomeDto homeDto) {
			// saving the homeDto attributes to the database
			return preferencesRepo.save(homeDto);
			
			
		}


		public HomeDto findByUserId(Long userId) {
			return preferencesRepo.findByUserId(userId);
			
		}
			
	
	
}
	

	
	
		
	
	
	
