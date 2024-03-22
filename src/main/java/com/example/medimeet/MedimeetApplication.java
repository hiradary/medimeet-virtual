package com.example.medimeet;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.medimeet.model.User;
import com.example.medimeet.repositories.AppointmentRepository;
import com.example.medimeet.repositories.DoctorDescRepository;
import com.example.medimeet.repositories.FeedbackRepository;
import com.example.medimeet.repositories.PrescriptionRepository;
import com.example.medimeet.repositories.UserRepository;

@SpringBootApplication
public class MedimeetApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedimeetApplication.class, args);
	}

    @Bean
    ApplicationRunner init(DoctorDescRepository doctorDescRepository,
                                  AppointmentRepository appointmentRepository, UserRepository userRepository, FeedbackRepository feedbackRepository,
                                  PrescriptionRepository prescriptionRepository) {
        return args -> {
            
            loadData(doctorDescRepository, appointmentRepository, userRepository, feedbackRepository, prescriptionRepository);
        };
    }
	
	private void loadData(DoctorDescRepository doctorDescRepository, 
    		AppointmentRepository appointmentRepository, UserRepository userRepository,FeedbackRepository feedbackRepository, 
    		PrescriptionRepository prescriptionRepository) {

		User testUser = new User("Hirad Arshadi", "hirad@gmail.com", "123456", "Doctor", "Vancouver", "+1 778 667 2361");
		
		userRepository.save(testUser);
	}
}
	
	
	
