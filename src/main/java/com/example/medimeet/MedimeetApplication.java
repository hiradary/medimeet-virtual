package com.example.medimeet;

import java.time.LocalDateTime;
import java.util.ArrayList; 
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MedimeetApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedimeetApplication.class, args);
	}

    @Bean
    ApplicationRunner init() {
        return args -> {
            
            
        };
    }
	
	
	private void loadData() {

	}
}
	
	
	
