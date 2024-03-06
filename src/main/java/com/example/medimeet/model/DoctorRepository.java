package com.example.medimeet.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{

	List<Doctor> findBySpecialization(String specialization);

   
    List<Doctor> findByAvailabilityBetween(LocalTime startTime, LocalTime endTime);

  
    List<Doctor> findBySpecializationAndAvailabilityBetween(String specialization, LocalTime startTime, LocalTime endTime);

    
    Doctor findByName(String name);

   
    boolean existsByAvailabilityDate(LocalDate date);
	
}
