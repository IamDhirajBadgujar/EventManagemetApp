package com.Dhiraj.eventManagemetApp.entity;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String location;
    private String role;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    private String  password;
    private String confirmpassword;// New field for Date of Birth

    public String getConfirmpassword() {
		return confirmpassword;
	}

	public UserDTO(Long id, String username, String email, String phone, String location, String role, Date dob,
			String password, String confirmpassword) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.location = location;
		this.role = role;
		this.dob = dob;
		this.password = password;
		this.confirmpassword = confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// Constructors
    public UserDTO() {}

    public UserDTO(Long id, String username, String email, String phone, String location, String role, Date dob) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.role = role;
        this.dob = dob;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Date getDob() { return dob; }
    public void setDob(Date dob) { this.dob = dob; }

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", username=" + username + ", email=" + email + ", phone=" + phone + ", location="
				+ location + ", role=" + role + ", dob=" + dob + ", password=" + password + ", confirmpassword="
				+ confirmpassword + "]";
	}
    
}
