package com.example.medimeet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.medimeet.model.Doctor;
import com.example.medimeet.repositories.DoctorRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors(
            @RequestParam(required = false) String specialization,
            @RequestParam(required = false) LocalTime startTime,
            @RequestParam(required = false) LocalTime endTime) {
        try {
            List<Doctor> doctors;
            if (specialization != null && startTime != null && endTime != null) {
                doctors = doctorRepository.findBySpecializationAndAvailabilityBetween(specialization, startTime, endTime);
            } else if (specialization != null) {
                doctors = doctorRepository.findBySpecialization(specialization);
            } else if (startTime != null && endTime != null) {
                doctors = doctorRepository.findByAvailabilityBetween(startTime, endTime);
            } else {
                doctors = doctorRepository.findAll();
            }

            if (doctors.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(doctors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<Doctor> getDoctorByName(@PathVariable String name) {
        try {
            Doctor doctor = doctorRepository.findByName(name);
            if (doctor != null) {
                return new ResponseEntity<>(doctor, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        try {
            Doctor newDoctor = doctorRepository.save(doctor);
            return new ResponseEntity<>(newDoctor, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/check-availability")
    public ResponseEntity<Boolean> checkDoctorAvailability(@RequestParam LocalDate date) {
        try {
            boolean available = doctorRepository.existsByAvailabilityDate(date);
            return new ResponseEntity<>(available, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctorDetails) {
        try {
            return doctorRepository.findById(id).map(existingDoctor -> {
                existingDoctor.setSpecialization(doctorDetails.getSpecialization());
                existingDoctor.setAvailability(doctorDetails.getAvailability());
                existingDoctor.setBio(doctorDetails.getBio());
                existingDoctor.setPhoto(doctorDetails.getPhoto());
                Doctor updatedDoctor = doctorRepository.save(existingDoctor);
                return new ResponseEntity<>(updatedDoctor, HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllDoctors() {
        try {
            doctorRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDoctor(@PathVariable Long id) {
        try {
            doctorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
