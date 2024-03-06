package com.example.medimeet.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.medimeet.model.Appointment;
import com.example.medimeet.model.Doctor;
import com.example.medimeet.model.User;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	
	List<Appointment> findByPatient(User patient);

    List<Appointment> findByDoctor(Doctor doctor);

    List<Appointment> findByAppointmentDate(LocalDate date);

    List<Appointment> findByStatus(String status);
    
    List<Appointment> findByPatientAndStatus(User patient, String status);

    List<Appointment> findByDoctorAndAppointmentDateBetween(Doctor doctor, LocalDate startDate, LocalDate endDate);

    List<Appointment> findByDoctorSpecializationAndAppointmentDateAfter(String specialization, LocalDate date);

    List<Appointment> findByDoctorSpecializationAndStatus(String specialization, String status);
}