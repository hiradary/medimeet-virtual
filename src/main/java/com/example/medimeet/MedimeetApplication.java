package com.example.medimeet;

import java.util.ArrayList; 
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.medimeet.repositories.AppointmentRepository;
import com.example.medimeet.model.Doctor;
import com.example.medimeet.repositories.DoctorRepository;
import com.example.medimeet.repositories.FeedbackRepository;
import com.example.medimeet.repositories.PrescriptionRepository;
import com.example.medimeet.model.User;
import com.example.medimeet.repositories.UserRepository;

@SpringBootApplication
public class MedimeetApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedimeetApplication.class, args);
	}

    @Bean
    ApplicationRunner init(DoctorRepository doctorRepository,
                                  AppointmentRepository appointmentRepository, UserRepository userRepository, FeedbackRepository feedbackRepository,
                                  PrescriptionRepository prescriptionRepository) {
        return args -> {
            
            loadData(doctorRepository, appointmentRepository, userRepository, feedbackRepository, prescriptionRepository);
        };
    }
	
	
	
	
	private void loadData(DoctorRepository doctorRepository, 
    		AppointmentRepository appointmentRepository, UserRepository userRepository,FeedbackRepository feedbackRepository, 
    		PrescriptionRepository prescriptionRepository) {
       User user = new User();
       
		 
        ArrayList<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor(user, "Cardiology", "Monday 9AM-12PM", "Experienced cardiologist specializing in heart diseases.", "doctor1.jpg"));  
        doctors.add( new Doctor(user, "Dermatology", "Tuesday 1PM-4PM", "Dermatologist with a focus on skincare treatments.", "doctor2.jpg"));
        doctors.add( new Doctor(user, "Endocrinology", "Wednesday 10AM-1PM", "Endocrinologist dedicated to hormone-related disorders.", "doctor3.jpg"));
        doctors.add( new Doctor(user, "Gastroenterology", "Thursday 2PM-5PM", "Gastroenterologist providing expert care for digestive issues.", "doctor4.jpg"));
        doctors.add( new Doctor(user, "Hematology", "Friday 11AM-2PM", "Hematologist specializing in blood disorders.", "doctor5.jpg"));
        doctorRepository.saveAll(doctors);

        
 }
}
	
	
	
