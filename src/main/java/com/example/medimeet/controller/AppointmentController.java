package com.example.medimeet.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

import com.example.medimeet.model.Appointment;
import com.example.medimeet.model.AppointmentRepository;
import com.example.medimeet.model.Doctor;
import com.example.medimeet.model.DoctorRespository;
import com.example.medimeet.model.User;
import com.example.medimeet.model.UserRepository;

@RestController
@RequestMapping("/api")
public class AppointmentController {

	@Autowired
	AppointmentRepository Appointrepo; 
	@Autowired
	DoctorRespository Doctorepo; 
	// To get a appointment
		@GetMapping("/appointments/{id}")
		public ResponseEntity<Appointment> getCourseById(@PathVariable("id") long id) {
			Optional<Appointment> appointmentData = Appointrepo.findById(id);

			if (appointmentData.isPresent()) {
				return new ResponseEntity<>(appointmentData.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		// Call the service to get appointments for the patients
		@GetMapping("/user/{uid}/appointments")
		public ResponseEntity<List<Appointment>> getUserAppointment(@PathVariable("uid") long uid) {
			List<Appointment> appointmentData = new ArrayList<>();
			appointmentData = Appointrepo.findByPatientId(uid);

		    if (!appointmentData.isEmpty()) {
		        return new ResponseEntity<>(appointmentData, HttpStatus.OK);
		    } else {
		        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		}
		// Call the service to get appointments for the doctors
		@GetMapping("/user/{uid}/appointments")
		public ResponseEntity<List<Appointment>> getDoctorAppointment(@PathVariable("uid") long uid) {
			List<Appointment> appointmentData = new ArrayList<>();
			appointmentData = Appointrepo.findByDoctorId(uid);

		    if (!appointmentData.isEmpty()) {
		        return new ResponseEntity<>(appointmentData, HttpStatus.OK);
		    } else {
		        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		}
		// Call the service to create appointment
		@PostMapping("/appointments")
		public ResponseEntity<Appointment> CreateAppointment(@RequestBody Appointment appointment) {
			try {
				LocalDateTime requestedDateTime = appointment.getDateTime();
				 // Retrieve doctor and user entities based on IDs in the appointment request
		        Doctor doctor = DoctorRespository.findById(appointment.getDoctor())
		                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

		        User user = UserRepository.findById(appointment.getUser())
		                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
		        // Check if the requested datetime is within the doctor's availability and not already booked
		        if (!isAppointmentValid(doctor, requestedDateTime)) {
		            return new ResponseEntity<>(HttpStatus.CONFLICT);
		        }
		       // Create the appointment
		        Appointment appointment1 = new Appointment();
		        appointment1.setDoctor(doctor);
		        appointment1.setUser(user);
		        appointment1.setDateTime(requestedDateTime);

		        // Save the appointment
		        //Appointrepo.saveAppointment(appointment);
		        Appointment _appointment = Appointrepo.save(appointment1);

				return new ResponseEntity<>(_appointment, HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		public boolean isAppointmentValid(Doctor doctor, LocalDateTime dateTime) {
	        // Check if the doctor is available at the specified time
	        boolean isDoctorAvailable = isDoctorAvailable(doctor, dateTime);

	        // Check if the specified datetime is available (not already booked)
	        boolean isDateTimeAvailable = isDateTimeAvailable(dateTime);

	        // Return true if both conditions are met
	        return isDoctorAvailable && isDateTimeAvailable;
	    }

	    private boolean isDoctorAvailable(Doctor doctor, LocalDateTime dateTime) {
	        // Implement your logic to check if the doctor is available at the specified time
	        // This could involve checking existing appointments for the doctor
	        List<Appointment> existingAppointments = Appointrepo.findByDoctorAndDateTime(doctor, dateTime);

	        // Return true if no existing appointments overlap with the requested time
	        return existingAppointments.isEmpty();
	    }

	    private boolean isDateTimeAvailable(LocalDateTime dateTime) {
	        // Implement your logic to check if the specified datetime is available
	        // This could involve checking existing appointments at the specified time

	        LocalDateTime startDateTime = dateTime.minusMinutes(15);
	        LocalDateTime endDateTime = dateTime.plusMinutes(15);

	        List<Appointment> existingAppointments = Appointrepo.findByDateTimeBetween(startDateTime, endDateTime);

	        // Return true if no existing appointments are found within the specified time range
	        return existingAppointments.isEmpty();
	    }
	 // To update a course
		@PutMapping("/appointments/{id}")
		public ResponseEntity<Appointment> updateAppointment(@PathVariable("id") long id, @RequestBody Appointment appointment) {
			Optional<Appointment> AppointmentData = Appointrepo.findById(id);

			if (AppointmentData.isPresent()) {
				Appointment _appointment = AppointmentData.get();
				_appointment.setDoctor(appointment.getDoctor());
				_appointment.setUser(appointment.getUser());
				_appointment.setDateTime(appointment.getDateTime());
				_appointment.setStatus(appointment.getStatus());

				return new ResponseEntity<>(Appointrepo.save(_appointment), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		// To delete a course
		@DeleteMapping("/appointments/{id}")
		public ResponseEntity<HttpStatus> deleteAppointment(@PathVariable("id") long id) {
			try {
				Appointrepo.deleteById(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

}
