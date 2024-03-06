package com.example.medimeet.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
	Optional<Prescription> findById(Long id);
	List<Prescription> findBypatient(Long id);
	
}
