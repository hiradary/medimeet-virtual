package com.example.crs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long feedbackID;

    @Column(nullable = false)
    private long appointmentID;

    @Column(nullable = false)
    private long patientID;

    @Column(nullable = false)
    private int rating;

    @Column(length = 1024)
    private String comment;

    public Feedback() {
    }

    public Feedback(Long appointmentID, Long patientID, int rating, String comment) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.rating = rating;
        this.comment = comment;
    }

    public Long getFeedbackID() {
        return feedbackID;
    }

    public Long getAppointmentID() {
        return appointmentID;
    }

    public Long getPatientID() {
        return patientID;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public void setFeedbackID(Long feedbackID) {
        this.feedbackID = feedbackID;
    }

    public void setAppointmentID(Long appointmentID) {
        this.appointmentID = appointmentID;
    }

    public void setPatientID(Long patientID) {
        this.patientID = patientID;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
