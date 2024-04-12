package com.example.medimeet.model;

import jakarta.persistence.*;

@Entity
public class DoctorDesc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorDescId;
    
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
    
    private String specialization;
    private String availability;
    private String bio;
    private String photo;
    
    public DoctorDesc() {}

    public DoctorDesc(User user, String specialization, String availability, String bio, String photo) {
        this.user = user;
        this.specialization = specialization;
        this.availability = availability;
        this.bio = bio;
        this.photo = photo;
    }
	public String getSpecialization() {
		return specialization;
	}
	
	public User getUser() {
		return user;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setUser(User user) {
			this.user = user;
		
	}
}