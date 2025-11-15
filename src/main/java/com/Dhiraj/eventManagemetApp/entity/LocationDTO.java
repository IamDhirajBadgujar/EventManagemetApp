package com.Dhiraj.eventManagemetApp.entity;

public class LocationDTO {
    private String country;
    private String state;
    private String city;
    // Getters and Setters
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public LocationDTO(String country, String state, String city) {
		super();
		this.country = country;
		this.state = state;
		this.city = city;
	}
	public LocationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
