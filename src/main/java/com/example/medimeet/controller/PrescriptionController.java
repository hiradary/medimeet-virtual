package com.example.medimeet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medimeet.model.AppointmentRepository;
import com.example.medimeet.model.DoctorRespository;
import com.example.medimeet.model.UserRepository;

@RestController
@RequestMapping("/api")
public class PrescriptionController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DoctorRespository doctorRepository;
	
	@Autowired
	AppointmentRepository appointmentRepository;
	
}
