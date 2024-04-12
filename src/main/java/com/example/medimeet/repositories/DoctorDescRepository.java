package com.example.medimeet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.medimeet.model.DoctorDesc;

public interface DoctorDescRepository extends JpaRepository<DoctorDesc, Long> {
	
}
