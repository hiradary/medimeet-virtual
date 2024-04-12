package com.example.medimeet.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.medimeet.model.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    List<Prescription> findByAppointmentAppointmentId(Long appointmentId);
	
	
}
