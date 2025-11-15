package com.Dhiraj.eventManagemetApp.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.Builder;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "events")
public class Event {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(name = "event_date", nullable = false)
    private LocalDateTime eventDate;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime  updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;
    
    private boolean isExpired;
    
    
    public boolean isExpired() {
		return isExpired;
	}
    
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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	private String genre;


	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "event_participants",
        joinColumns = @JoinColumn(name = "event_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> participants = new HashSet<>();

    private int maxParticipants;
    private String imageName; // e.g., "gaming.png"


    
    public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}

	@PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
       
        this.isExpired = false;
    }

    

    // Fix: Correct return type for participants getter
    public Set<User> getParticipants() {
        return participants;
    }

    // Fix: Implement setCreatedBy correctly
    public void setCreatedBy(User user) {
        this.createdBy = user;
    }

    public boolean hasExpired() {
        return eventDate.isBefore(LocalDateTime.now());
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

	public int getMaxParticipants() {
		return maxParticipants;
	}

	public void setMaxParticipants(int maxParticipants) {
		this.maxParticipants = maxParticipants;
	}

	
	public User getCreatedBy() {
		return createdBy;
	}

	public void setParticipants(Set<User> participants) {
		this.participants = participants;
	}

	

	public Event(Long id, String name, String description, String location, LocalDateTime eventDate,
			LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, boolean isExpired, String country,
			String state, String city, String genre, Set<User> participants, int maxParticipants, String imageName) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.location = location;
		this.eventDate = eventDate;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.createdBy = createdBy;
		this.isExpired = isExpired;
		this.country = country;
		this.state = state;
		this.city = city;
		this.genre = genre;
		this.participants = participants;
		this.maxParticipants = maxParticipants;
		this.imageName = imageName;
	}

	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", description=" + description + ", location=" + location
				+ ", eventDate=" + eventDate + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", createdBy="
				+ createdBy + ", isExpired=" + isExpired + ", participants=" + participants + ", maxParticipants="
				+ maxParticipants + ", isExpired= "+isExpired +"]";
	}
	
	@PreUpdate
	protected void onUpdate() {
	    updatedAt = LocalDateTime.now();
	    this.isExpired = eventDate.isBefore(LocalDateTime.now());
	}



    
    
    
}
