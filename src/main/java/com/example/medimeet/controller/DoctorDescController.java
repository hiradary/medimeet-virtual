package com.example.medimeet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.medimeet.model.DoctorDesc;
import com.example.medimeet.repositories.DoctorDescRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doctordesc")
public class DoctorDescController {

    @Autowired
    private DoctorDescRepository doctorDescRepository;

    @GetMapping
    public ResponseEntity<List<DoctorDesc>> getAllDoctorDescs() {
        List<DoctorDesc> doctorDescs = doctorDescRepository.findAll();
        return ResponseEntity.ok(doctorDescs);
    }

    @PostMapping
    public ResponseEntity<DoctorDesc> createDoctorDesc(@RequestBody DoctorDesc doctorDesc) {
        DoctorDesc savedDoctorDesc = doctorDescRepository.save(doctorDesc);
        return new ResponseEntity<>(savedDoctorDesc, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDesc> getDoctorDescById(@PathVariable Long id) {
        Optional<DoctorDesc> doctorDesc = doctorDescRepository.findById(id);
        return doctorDesc.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDesc> updateDoctorDesc(@PathVariable Long id, @RequestBody DoctorDesc doctorDescDetails) {
        return doctorDescRepository.findById(id).map(doctorDesc -> {
            doctorDesc.setSpecialization(doctorDescDetails.getSpecialization());
            doctorDesc.setAvailability(doctorDescDetails.getAvailability());
            doctorDesc.setBio(doctorDescDetails.getBio());
            doctorDesc.setPhoto(doctorDescDetails.getPhoto());
            DoctorDesc updatedDoctorDesc = doctorDescRepository.save(doctorDesc);
            return ResponseEntity.ok(updatedDoctorDesc);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDoctorDesc(@PathVariable Long id) {
        return doctorDescRepository.findById(id).map(doctorDesc -> {
            doctorDescRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
