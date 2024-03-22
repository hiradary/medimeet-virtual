package com.example.medimeet.model;

import jakarta.persistence.*;

@Entity
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescriptionId;

    @ManyToOne
    @JoinColumn(name = "appointmentId", referencedColumnName = "appointmentId")
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "patientId", referencedColumnName = "userId")
    private User patient;

    private String medication;
    private String dosage;
    private String duration;
    
	public String getMedication() {
		return medication;
	}
	
	public void setMedication(String medication) {
		this.medication = medication;
	}
	
	public String getDosage() {
		return dosage;
	}
	
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
}
