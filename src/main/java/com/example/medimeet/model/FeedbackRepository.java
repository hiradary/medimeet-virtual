package com.example.medimeet.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
 
	List<Feedback> findAllFeedback();
    
	List<Feedback> findByPatientId(Long patientId);
   
    List<Feedback> findByPatient(User patient);
   
    List<Feedback> findByRating(int rating);
   
    List<Feedback> findByRatingGreaterThanEqual(int rating);
    
    List<Feedback> findByCommentContaining(String keyword);
    
    List<Feedback> findByCommentContainingIgnoreCase(String keyword);
    
    Feedback findByAppointmentAndPatient(Appointment appointment, User patient);
    
    Feedback findByAppointment(Appointment appointment);
    
    Double calculateAverageRatingByAppointment(Appointment appointment);
    
    Long countByPatient(User patient);
    
    Double calculateAverageRatingByDoctor(Doctor doctor);

	Feedback getFeedbackById(Long id);

	List<Feedback> findAll();
   
   

 

    
   

}
