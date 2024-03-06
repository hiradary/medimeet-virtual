package com.example.medimeet.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Prescription")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescriptionID;

    @ManyToOne
    @JoinColumn(name = "AppointmentID")
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "PatientID")
    private User patient;

	@Column(name = "medication")
    private String medication;
	
	@Column(name = "dosage")
    private String dosage;
	
	@Column(name = "duration")
    private String duration;
	
	public Prescription() {
		
	}
	
	public Prescription(User patient, String medication, String dosage, String duration) {
		this.patient = patient;
		this.medication = medication;
		this.dosage = dosage;
		this.duration = duration;
	}

	public Long getPrescriptionID() {
		return prescriptionID;
	}

	public void setPrescriptionID(Long prescriptionID) {
		this.prescriptionID = prescriptionID;
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
