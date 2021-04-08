package com.coderscampus.responseObject;

import java.util.ArrayList;
import java.util.List;


// This class is for storing the API data -- and has the same structured data from the API<MarsPhoto>


// This class allows to store a list of <MarsPhoto> that stores< (API Data) including camera object
public class MarsRoverApiResponse {
	
	//Storing the API data in this object 
	List<MarsPhoto> photos = new ArrayList<>();
	
	

	public List<MarsPhoto> getPhotos() {
		return photos;
	}



	public void setPhotos(List<MarsPhoto> photos) {
		this.photos = photos;
	}



	@Override
	public String toString() {
		return "MarsRoverApiResponse [photos=" + photos + "]";
	}
	
	
}