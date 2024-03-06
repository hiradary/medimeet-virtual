package com.example.medimeet.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

	@Entity
	@Table(name = "feedbacks")
	public class Feedback {
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "feedback_id")
	    private Long feedbackId;

	    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	    @JoinColumn(name = "appointment_id")
	    private Appointment appointment;

	    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	    @JoinColumn(name = "patient_id")
	    private User patient;

	    
	  
	    @Column(name = "rating")
	    private int rating;

	    @Column(name = "comment")
	    private String comment;

	    
		public Feedback() {
			
		}
		
	
		public Feedback(Appointment appointment, User patient, int rating, String comment) {
			
	
			this.appointment = appointment;
			this.patient = patient;
			this.rating = rating;
			this.comment = comment;
		}



		public Long getFeedbackId() {
	        return feedbackId;
	    }

	    public void setFeedbackId(Long feedbackId) {
	        this.feedbackId = feedbackId;
	    }

	    public Appointment getAppointment() {
	        return appointment;
	    }

	    public void setAppointment(Appointment appointment) {
	        this.appointment = appointment;
	    }

	    public User getPatient() {
	        return patient;
	    }

	    public void setPatient(User patient) {
	        this.patient = patient;
	    }

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
	
	
