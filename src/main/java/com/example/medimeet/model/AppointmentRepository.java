package com.example.medimeet.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {

	Optional<User> findByFeedbackId(long id);
	
	List<Appointment> findByPatient(User patient);

    List<Appointment> findByDoctor(Doctor doctor);

    List<Appointment> findByAppointmentDate(LocalDate date);

    List<Appointment> findByStatus(String status);
    
    List<Appointment> findByPatientAndStatus(User patient, String status);

    List<Appointment> findByDoctorAndAppointmentDateBetween(Doctor doctor, LocalDate startDate, LocalDate endDate);

    List<Appointment> findByDoctorSpecializationAndAppointmentDateAfter(String specialization, LocalDate date);

    List<Appointment> findByDoctorSpecializationAndStatus(String specialization, String status);

	Optional<User> findByAppointmentId(long id);

	Optional<User> getAppointmentId();

	Optional<User> findById(long id);
}
