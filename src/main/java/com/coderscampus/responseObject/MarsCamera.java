package com.coderscampus.responseObject;

import com.fasterxml.jackson.annotation.JsonProperty;

// camera needs its own class from API object

public class MarsCamera {
	
	private Long id;
	String name; 
	
	@JsonProperty("rover_id")
	private Long roverId;
	
	@JsonProperty("full_name")
	private String fullName;
	 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getRoverId() {
		return roverId;
	}
	public void setRoverId(Long roverId) {
		this.roverId = roverId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	
}