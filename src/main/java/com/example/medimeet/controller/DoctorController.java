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
@RequestMapping("/api")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    
    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        try {
            List<Doctor> doctors = doctorRepository.findAll();
            if (doctors.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(doctors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   
    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<List<Doctor>> getDoctorsBySpecialization(@PathVariable String specialization) {
        try {
            List<Doctor> doctors = doctorRepository.findBySpecialization(specialization);
            if (doctors.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(doctors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @GetMapping("/availability")
    public ResponseEntity<List<Doctor>> getAvailableDoctors(
            @RequestParam LocalTime startTime,
            @RequestParam LocalTime endTime) {
        try {
            List<Doctor> doctors = doctorRepository.findByAvailabilityBetween(startTime, endTime);
            if (doctors.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(doctors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @GetMapping("/specialization-availability")
    public ResponseEntity<List<Doctor>> getDoctorsBySpecializationAndAvailability(
            @RequestParam String specialization,
            @RequestParam LocalTime startTime,
            @RequestParam LocalTime endTime) {
        try {
            List<Doctor> doctors = doctorRepository.findBySpecializationAndAvailabilityBetween(specialization, startTime, endTime);
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

    @PutMapping("/doctors/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable(value = "id") Long id, @RequestBody Doctor doctorDetails) {
        try {
            Optional<Doctor> existingDoctorOptional = doctorRepository.findById(id);
            if (existingDoctorOptional.isPresent()) {
                Doctor existingDoctor = existingDoctorOptional.get();
                existingDoctor.setSpecialization(doctorDetails.getSpecialization());
                existingDoctor.setAvailability(doctorDetails.getAvailability());
                existingDoctor.setBio(doctorDetails.getBio());
                existingDoctor.setPhoto(doctorDetails.getPhoto());
                    
                return new ResponseEntity<>(doctorRepository.save(existingDoctor), HttpStatus.OK);
            }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        } 
        }catch (Exception e) {
                return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
    
    @DeleteMapping("/doctors")
    public ResponseEntity<HttpStatus> deleteAllDoctors() {
        try {
            doctorRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
        	return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<HttpStatus> deleteDoctor(@PathVariable Long id) {
        try {
            doctorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    
    

}