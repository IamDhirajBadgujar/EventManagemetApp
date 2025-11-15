package com.Dhiraj.eventManagemetApp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "locations")
public class EventLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;

    private String state;

    private String city;

    // Constructors
    public EventLocation() {}

    public EventLocation(String country, String state, String city) {
        this.country = country;
        this.state = state;
        this.city = city;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
