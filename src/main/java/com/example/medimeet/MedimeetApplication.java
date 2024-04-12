package com.example.medimeet;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.medimeet.model.DoctorDesc;
import com.example.medimeet.model.User;
import com.example.medimeet.repositories.AppointmentRepository;
import com.example.medimeet.repositories.DoctorDescRepository;
import com.example.medimeet.repositories.FeedbackRepository;
import com.example.medimeet.repositories.PrescriptionRepository;
import com.example.medimeet.repositories.UserRepository;
import com.example.medimeet.utils.SimpleHashingTool;

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
            AppointmentRepository appointmentRepository, UserRepository userRepository, FeedbackRepository feedbackRepository, 
            PrescriptionRepository prescriptionRepository) {

        User testUser = new User("Hirad Arshadi", "hirad@gmail.com", SimpleHashingTool.hashPassword("123456"), "Doctor", "Vancouver", "+1 778 667 2361");
        userRepository.save(testUser);

        User testDoctor = new User("Krisha Mahat", "krishamahat751@gmail.com", 
                SimpleHashingTool.hashPassword("123456"), "Doctor", 
                "60 Ave, V3X 2N5", "6043600173");
        userRepository.save(testDoctor);

        // Create a new DoctorDesc object
		/*
		 * DoctorDesc doctorDesc = new DoctorDesc(); doctorDesc.setUser(testDoctor);
		 * doctorDesc.setSpecialization("Cardiology");
		 * doctorDesc.setAvailability("Morning (9:00 AM - 12:00 PM)");
		 * doctorDesc.setBio("Experienced cardiologist with a focus on preventive care."
		 * );
		 * 
		 * // Save the DoctorDesc object doctorDescRepository.save(doctorDesc);
		 */
    }

}
	
	
	
