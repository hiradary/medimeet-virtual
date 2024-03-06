package com.example.medimeet.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.medimeet.model.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
	Optional<Prescription> findById(Long id);
	List<Prescription> findByPatient(Long id);
}
