package com.example.medimeet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.medimeet.model.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
	
}
