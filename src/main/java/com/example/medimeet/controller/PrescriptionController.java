package com.example.medimeet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.medimeet.model.Appointment;
import com.example.medimeet.model.DoctorDesc;
import com.example.medimeet.model.Prescription;
import com.example.medimeet.model.User;
import com.example.medimeet.repositories.AppointmentRepository;
import com.example.medimeet.repositories.PrescriptionRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private AppointmentRepository appointmentRepo;

    @GetMapping
    public ResponseEntity<List<Prescription>> getAllPrescriptions() {
        List<Prescription> prescriptions = prescriptionRepository.findAll();
        return ResponseEntity.ok(prescriptions);
    }

	 @PostMapping
	 public ResponseEntity<Prescription> createPrescription(@RequestBody Prescription prescription) {
		 System.out.println(prescription);
		 Prescription savedPrescription = prescriptionRepository.save(prescription); 
		 return new ResponseEntity<>(savedPrescription, HttpStatus.CREATED); 
		 }
	 
    @GetMapping("/{id}")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable Long id) {
        Optional<Prescription> prescription = prescriptionRepository.findById(id);
        return prescription.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<List<Prescription>> getPrescriptionsByAppointmentId(@PathVariable Long appointmentId) {
        List<Prescription> prescriptions = prescriptionRepository.findByAppointmentAppointmentId(appointmentId);
        return ResponseEntity.ok(prescriptions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prescription> updatePrescription(@PathVariable Long id, @RequestBody Prescription prescriptionDetails) {
        return prescriptionRepository.findById(id).map(prescription -> {
            prescription.setMedication(prescriptionDetails.getMedication());
            prescription.setDosage(prescriptionDetails.getDosage());
            prescription.setDuration(prescriptionDetails.getDuration());
            Prescription updatedPrescription = prescriptionRepository.save(prescription);
            return ResponseEntity.ok(updatedPrescription);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePrescription(@PathVariable Long id) {
        return prescriptionRepository.findById(id).map(prescription -> {
            prescriptionRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
