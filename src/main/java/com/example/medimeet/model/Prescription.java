package com.example.medimeet.model;



import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "prescriptions")
public class Prescription {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	 @ManyToOne
	    @JoinColumn(name = "patient_id", nullable = false)
	    private User patient;

	    @Column(name = "medication_name", nullable = false)
	    private String medicationName;

	    @Column(name = "dosage")
	    private String dosage;

	    @Column(name = "instructions")
	    private String instructions;

	    @Column(name = "prescription_date", nullable = false)
	    private LocalDate prescriptionDate;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public User getPatient() {
			return patient;
		}

		public void setPatient(User patient) {
			this.patient = patient;
		}

		public String getMedicationName() {
			return medicationName;
		}

		public void setMedicationName(String medicationName) {
			this.medicationName = medicationName;
		}

		public String getDosage() {
			return dosage;
		}

		public void setDosage(String dosage) {
			this.dosage = dosage;
		}

		public String getInstructions() {
			return instructions;
		}

		public void setInstructions(String instructions) {
			this.instructions = instructions;
		}

		public LocalDate getPrescriptionDate() {
			return prescriptionDate;
		}

		public void setPrescriptionDate(LocalDate prescriptionDate) {
			this.prescriptionDate = prescriptionDate;
		}

		public Prescription(User patient, String medicationName, String dosage, String instructions,
				LocalDate prescriptionDate) {
			super();
			this.patient = patient;
			this.medicationName = medicationName;
			this.dosage = dosage;
			this.instructions = instructions;
			this.prescriptionDate = prescriptionDate;
		}
		public Prescription() {
			
		}
}
