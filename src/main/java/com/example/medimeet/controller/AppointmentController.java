package com.example.medimeet.controller;

import com.example.medimeet.model.Appointment;
import com.example.medimeet.model.Doctor;
import com.example.medimeet.model.User;
import com.example.medimeet.repositories.AppointmentRepository;
import com.example.medimeet.repositories.DoctorRepository;
import com.example.medimeet.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Autowired
    private DoctorRepository doctorRepository;
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        try {
            Appointment savedAppointment = appointmentRepository.save(appointment);
            return ResponseEntity.ok(savedAppointment);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    
    @GetMapping
    public ResponseEntity<List<Appointment>> getAppointments(
            @RequestParam(required = false) Long patientId,
            @RequestParam(required = false) Long doctorId,
            @RequestParam(required = false) String date,
            @RequestParam(required = false) String status) {
        try {
            if (patientId != null && doctorId == null && date == null && status == null) {
                Optional<User> patient = userRepository.findById(patientId);
                return patient.map(user -> ResponseEntity.ok(appointmentRepository.findByPatient(user)))
                              .orElseGet(() -> ResponseEntity.notFound().build());
            } else if (doctorId != null && patientId == null && date == null && status == null) {
                Optional<Doctor> doctor = doctorRepository.findById(doctorId);
                return doctor.map(doc -> ResponseEntity.ok(appointmentRepository.findByDoctor(doc)))
                             .orElseGet(() -> ResponseEntity.notFound().build());
            } else if (date != null && patientId == null && doctorId == null && status == null) {
                LocalDate parsedDate = LocalDate.parse(date);
                List<Appointment> appointments = appointmentRepository.findByAppointmentDate(parsedDate);
                return ResponseEntity.ok(appointments);
            } else if (status != null && patientId == null && doctorId == null && date == null) {
                List<Appointment> appointments = appointmentRepository.findByStatus(status);
                return ResponseEntity.ok(appointments);
            } else {
                List<Appointment> appointments = appointmentRepository.findAll();
                return ResponseEntity.ok(appointments);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointmentDetails) {
        try {
            return appointmentRepository.findById(id).map(appointment -> {
                // Assume all setters are available and update necessary fields
                appointment.setPatient(appointmentDetails.getPatient());
                appointment.setDoctor(appointmentDetails.getDoctor());
                appointment.setAppointmentDate(appointmentDetails.getAppointmentDate());
                appointment.setAppointmentTime(appointmentDetails.getAppointmentTime());
                appointment.setStatus(appointmentDetails.getStatus());
                Appointment updatedAppointment = appointmentRepository.save(appointment);
                return ResponseEntity.ok(updatedAppointment);
            }).orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        try {
            if (appointmentRepository.existsById(id)) {
                appointmentRepository.deleteById(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
