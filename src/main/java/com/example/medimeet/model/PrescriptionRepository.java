package com.example.medimeet.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
	
	Optional<Prescription> findByPrescriptionId(Long prescrptionId);
	
	Optional<Prescription> findByAppointmentId(Long appointmentId);
	
	Optional<Prescription> findByPatientId(Long patientId);
	
}
