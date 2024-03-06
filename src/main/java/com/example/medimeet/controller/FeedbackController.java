package com.example.medimeet.controller;

import java.util.List;

import org.apache.el.stream.Optional;
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

import com.example.medimeet.model.Feedback;
import com.example.medimeet.repositories.FeedbackRepository;

@RestController
@RequestMapping("/api")
public class FeedbackController {
	
	 
	    @Autowired
	    private FeedbackRepository feedbackRepository;

	    @GetMapping
	    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
	        try {
	            List<Feedback> feedbacks = feedbackRepository.findAll();
	            if (feedbacks.isEmpty()) {
	                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	            }
	            return new ResponseEntity<>(feedbacks, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long id) {
	        try {
	            Feedback feedback = feedbackRepository.findById(id).orElse(null);
	            if (feedback == null) {
	                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	            }
	            return new ResponseEntity<>(HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @GetMapping("/rating/{rating}")
	    public ResponseEntity<List<Feedback>> getFeedbacksByRating(@PathVariable int rating) {
	        try {
	            List<Feedback> ratingFeedbacks = feedbackRepository.findByRating(rating);
	            if (ratingFeedbacks.isEmpty()) {
	                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	            }
	            return new ResponseEntity<>(ratingFeedbacks, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    
	    
	    @PostMapping
	    public ResponseEntity<Feedback> addFeedback(@RequestBody Feedback feedback) {
	        try {
	            Feedback savedFeedback = feedbackRepository.save(feedback);
	            return new ResponseEntity<>(savedFeedback, HttpStatus.CREATED);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @PutMapping("/feedbacks/{id}")
	    public ResponseEntity<Feedback> updateFeedback(@PathVariable Long id, @RequestBody Feedback feedback) {
	        try {
	            if (!feedbackRepository.existsById(id)) {
	                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	            }
	            feedback.setFeedbackId(id);
	            return new ResponseEntity<>(HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	   
	    @DeleteMapping("/{id}")
	    public ResponseEntity<HttpStatus> deleteFeedback(@PathVariable Long id) {
	        try {
	        	feedbackRepository.deleteById(id);
	        	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    @DeleteMapping
	    public ResponseEntity<HttpStatus> deleteAllFeedbacks() {
	        try {
	            feedbackRepository.deleteAll();
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	}
	    
	    
	    

	
	
	
