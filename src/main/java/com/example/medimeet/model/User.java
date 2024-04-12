package com.example.medimeet.model;

import jakarta.persistence.*;

@Entity
@Table(name = "`user`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String name;
    private String email;
    private String password;
    private String userType;
    private String address;
    private String phone;
    
    public User() {
    	
    }
    
    public User(String name, String email, String password, String userType, String address, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.address = address;
        this.phone = phone;
    }
    
    public Long getUserId() {
    	return userId;
    }
    
    public void setUserId(Long userId) {
    	this.userId = userId;
    }
    
    
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserType() {
		return userType;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
}