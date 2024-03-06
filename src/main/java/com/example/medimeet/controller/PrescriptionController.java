package com.example.medimeet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medimeet.model.Prescription;
import com.example.medimeet.model.PrescriptionRepository;
import com.example.medimeet.model.UserRepository;

@RestController
@RequestMapping("/api")
public class PrescriptionController {
@Autowired 
PrescriptionRepository prescriptionRepository;

@Autowired
UserRepository userRepository;

//Endpoint to get all prescriptions
@GetMapping("/prescriptions")
public ResponseEntity<List<Prescription>> getAllPrescriptions() {
    List<Prescription> prescriptions = prescriptionRepository.findAll();
    return new ResponseEntity<>(prescriptions, HttpStatus.OK);
}
// Endpoint to get a prescription by ID
@GetMapping("/{id}")
public ResponseEntity<Prescription> getPrescriptionById(@PathVariable("id") long id) {
	   Optional<Prescription> prescriptionOptional = prescriptionRepository.findById(id);

	    if (prescriptionOptional.isPresent()) {
	        Prescription prescription = prescriptionOptional.get();
	        return new ResponseEntity<>(prescription, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
@GetMapping("/prescriptions/user/{userId}")
public ResponseEntity<List<Prescription>> getPrescriptionsByUserId(@PathVariable("userId") long userId) {
    List<Prescription> prescriptions = prescriptionRepository.findBypatient(userId);

    if (!prescriptions.isEmpty()) {
        return new ResponseEntity<>(prescriptions, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
//Endpoint to create a new prescription
@PostMapping("/prescriptions")
public ResponseEntity<Prescription> createPrescription(@RequestBody Prescription prescription) {
    // Ensure that the patient (User) exists
    if (userRepository.userExists(prescription.getPatient().getId())) {
        Prescription createdPrescription = prescriptionRepository.save(prescription);
        return new ResponseEntity<>(createdPrescription, HttpStatus.CREATED);
    } else {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
// Endpoint to update a prescription
@PutMapping("/prescriptions/{id}")
public ResponseEntity<Prescription> updatePrescription(@PathVariable("id") long id, @RequestBody Prescription updatedPrescription) {
    Optional<Prescription> existingPrescriptionOptional = prescriptionRepository.findById(id);

    if (existingPrescriptionOptional.isPresent()) {
        Prescription existingPrescription = existingPrescriptionOptional.get();

        // Ensure that the patient (User) exists
        if (userRepository.userExists(updatedPrescription.getPatient().getId())) {
            existingPrescription.setMedicationName(updatedPrescription.getMedicationName());
            existingPrescription.setDosage(updatedPrescription.getDosage());
            existingPrescription.setInstructions(updatedPrescription.getInstructions());
            existingPrescription.setPrescriptionDate(updatedPrescription.getPrescriptionDate());

            Prescription updatedPrescriptionEntity = prescriptionRepository.save(existingPrescription);
            return new ResponseEntity<>(updatedPrescriptionEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

// Endpoint to delete a prescription by ID
@DeleteMapping("/prescriptions/{id}")
public ResponseEntity<Void> deletePrescription(@PathVariable("id") long id) {
    Optional<Prescription> prescription = prescriptionRepository.findById(id);
    if (prescription.isPresent()) {
    	prescriptionRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
//To delete all courses
	@DeleteMapping("/prescriptions")
	public ResponseEntity<HttpStatus> deleteAllCourses() {
		try {
			prescriptionRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
