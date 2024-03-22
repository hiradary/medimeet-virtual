package com.example.medimeet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.medimeet.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	
}
