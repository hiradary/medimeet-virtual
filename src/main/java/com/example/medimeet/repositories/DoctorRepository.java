
package com.example.medimeet.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.medimeet.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{

	List<Doctor> findBySpecialization(String specialization);


    List<Doctor> findByAvailabilityBetween(LocalDateTime startTime, LocalDateTime endTime);


    List<Doctor> findBySpecializationAndAvailabilityBetween(String specialization, LocalDateTime startTime, LocalDateTime endTime);


    Doctor findByName(String name);


//    boolean existsByAvailabilityDate(LocalDate date);

}