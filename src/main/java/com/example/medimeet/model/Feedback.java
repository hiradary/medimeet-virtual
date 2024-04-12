package com.example.medimeet.model;

import jakarta.persistence.*;

@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;

    @ManyToOne
    @JoinColumn(name = "appointmentId", referencedColumnName = "appointmentId")
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "patientId", referencedColumnName = "userId")
    private User patient;

    private int rating;
    private String comment;
    
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
}
