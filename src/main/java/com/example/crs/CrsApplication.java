package com.example.crs;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.crs.model.FeedbackRepository;

@SpringBootApplication
public class CrsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrsApplication.class, args);
	}

	@Bean
	ApplicationRunner init(FeedbackRepository feedbackRepository) {
		return args -> {

		};
	}

}
