package com.example.medimeet.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	Optional<Appointment> findById(Long aid);
    List<Appointment> findByPatientId(Long patientId);
	List<Appointment> findByDoctorId(Long did);
    List<Appointment> findByDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
    List<Appointment> findByDoctorAndDateTime(@Param("doctor") Doctor doctor, @Param("dateTime") LocalDateTime dateTime);
}