package com.Dhiraj.eventManagemetApp.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;
    private String location;
    private String role;

    
    private Date dateCreated;

    @Temporal(TemporalType.DATE)
    private Date dob;

    // Default Constructor
    public User() {}

    // Constructor with all fields
    public User(Long id, String username, String password, String email, String phone, String location, String role, Date dateCreated, Date dob) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.role = role;
        this.dateCreated = dateCreated;
        this.dob = dob;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Date getDateCreated() { return dateCreated; }
    public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }

    public Date getDob() { return dob; }
    public void setDob(Date dob) { this.dob = dob; }

    // toString Method
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", location='" + location + '\'' +
                ", role='" + role + '\'' +
                ", dateCreated=" + dateCreated +
                ", dob=" + dob +
                '}';
    }
}
