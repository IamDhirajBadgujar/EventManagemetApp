package com.Dhiraj.eventManagemetApp.entity;

import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;



public class EventDTO {
    private Long id;
    private String name;
    private String description;
    private String location;
    private LocalDateTime eventDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long createdById;
    private Set<Long> participantIds;
    private boolean isExpired;
    private int maxParticipants;
    private String imageName; // e.g., "gaming.png"
    private String country;
    private String state;
    private String city;

 
    
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
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public boolean isExpired() {
		return isExpired;
	}
	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public LocalDateTime getEventDate() {
		return eventDate;
	}
	public void setEventDate(LocalDateTime eventDate) {
		this.eventDate = eventDate;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Long getCreatedById() {
		return createdById;
	}
	public void setCreatedById(Long createdById) {
		this.createdById = createdById;
	}
	public Set<Long> getParticipantIds() {
		return participantIds;
	}
	public void setParticipantIds(Set<Long> participantIds) {
		this.participantIds = participantIds;
	}
	public int getMaxParticipants() {
		return maxParticipants;
	}
	public void setMaxParticipants(int maxParticipants) {
		this.maxParticipants = maxParticipants;
	}
	
	
	public EventDTO(Long id, String name, String description, String location, LocalDateTime eventDate,
			LocalDateTime createdAt, LocalDateTime updatedAt, Long createdById, Set<Long> participantIds,
			boolean isExpired, int maxParticipants, String imageName, String country, String state, String city) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.location = location;
		this.eventDate = eventDate;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.createdById = createdById;
		this.participantIds = participantIds;
		this.isExpired = isExpired;
		this.maxParticipants = maxParticipants;
		this.imageName = imageName;
		this.country = country;
		this.state = state;
		this.city = city;
	}
	public EventDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EventDTO(Long id, String name, String description, String location, LocalDateTime eventDate,Long createdById,boolean isExpired,String imageName) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.name = name;
		this.description = description;
		this.location = location;
		this.eventDate = eventDate;
		this.isExpired=isExpired;
		this.createdById=createdById;
		this.imageName = imageName;
		
	}
	
	@Override
	public String toString() {
		return "EventDTO [id=" + id + ", name=" + name + ", description=" + description + ", location=" + location
				+ ", eventDate=" + eventDate + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", createdById=" + createdById + ", participantIds=" + participantIds + ", maxParticipants="
				+ maxParticipants +  "]";
	}
    
    
    
}
