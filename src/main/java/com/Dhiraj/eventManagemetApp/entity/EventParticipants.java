package com.Dhiraj.eventManagemetApp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "event_participants") // Renamed table to follow conventions
public class EventParticipants {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false) // Proper foreign key reference
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Proper foreign key reference
    private User user;
    
    @Column(name = "joined_date", nullable = false)
    private LocalDateTime joinedDate;

    @Column(name = "status", nullable = false)
    private String status; // Example values: "PENDING", "CONFIRMED", "CANCELED"

    @Column(name = "is_event_active", nullable = false)
    private boolean isEventActive;

    @Column(name = "date_modified", nullable = false)
    private LocalDateTime dateModified = LocalDateTime.now();

    @Column(name = "is_now_joined", nullable = false)
    private boolean isNowJoined;

    // Constructors
    public EventParticipants() {
        this.joinedDate = LocalDateTime.now();
        this.dateModified = LocalDateTime.now();
        this.isEventActive = true;
        this.isNowJoined = true;
        this.status = "CONFIRMED";
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDateTime joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isEventActive() {
        return isEventActive;
    }

    public void setEventActive(boolean eventActive) {
        isEventActive = eventActive;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }

    public boolean isNowJoined() {
        return isNowJoined;
    }

    public void setNowJoined(boolean nowJoined) {
        isNowJoined = nowJoined;
    }
}
