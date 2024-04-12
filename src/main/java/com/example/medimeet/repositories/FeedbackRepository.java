package com.example.medimeet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.medimeet.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
	
}