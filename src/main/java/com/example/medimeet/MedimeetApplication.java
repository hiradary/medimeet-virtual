package com.example.medimeet;

import java.util.ArrayList; 
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.medimeet.model.AppointmentRepository;
import com.example.medimeet.model.Doctor;
import com.example.medimeet.model.DoctorRepository;
import com.example.medimeet.model.FeedbackRepository;
import com.example.medimeet.model.PrescriptionRepository;
import com.example.medimeet.model.User;
import com.example.medimeet.model.UserRepository;

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
        doctors.add(new Doctor(user.getUserId(), "Cardiology", "Monday 9AM-12PM", "Experienced cardiologist specializing in heart diseases.", "doctor1.jpg"));  
        doctors.add( new Doctor(user.getUserId(), "Dermatology", "Tuesday 1PM-4PM", "Dermatologist with a focus on skincare treatments.", "doctor2.jpg"));
        doctors.add( new Doctor(user.getUserId(), "Endocrinology", "Wednesday 10AM-1PM", "Endocrinologist dedicated to hormone-related disorders.", "doctor3.jpg"));
        doctors.add( new Doctor(user.getUserId(), "Gastroenterology", "Thursday 2PM-5PM", "Gastroenterologist providing expert care for digestive issues.", "doctor4.jpg"));
        doctors.add( new Doctor(user.getUserId(), "Hematology", "Friday 11AM-2PM", "Hematologist specializing in blood disorders.", "doctor5.jpg"));
        doctorRepository.saveAll(doctors);

        
 }
}
	
	
	

