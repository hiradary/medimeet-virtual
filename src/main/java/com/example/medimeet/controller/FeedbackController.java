package com.example.medimeet.controller;

import java.util.List;

import java.util.Optional;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.medimeet.model.Feedback;
import com.example.medimeet.repositories.FeedbackRepository;
@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {
    
    @Autowired
    private FeedbackRepository feedbackRepository;

    // Get all feedbacks or filter by rating if rating parameter is provided
    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedbacks(@RequestParam(required = false) Integer rating) {
        try {
            List<Feedback> feedbacks;
            if (rating == null) {
                feedbacks = feedbackRepository.findAll();
            } else {
                feedbacks = feedbackRepository.findByRating(rating);
            }
            if (feedbacks.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(feedbacks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get a single feedback by ID
    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long id) {
        try {
            Optional<Feedback> feedback = feedbackRepository.findById(id);
            return feedback.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                           .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Add a new feedback
    @PostMapping
    public ResponseEntity<Feedback> addFeedback(@RequestBody Feedback feedback) {
        try {
            Feedback savedFeedback = feedbackRepository.save(feedback);
            return new ResponseEntity<>(savedFeedback, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an existing feedback
    @PutMapping("/{id}")
    public ResponseEntity<Feedback> updateFeedback(@PathVariable Long id, @RequestBody Feedback feedback) {
        try {
            return feedbackRepository.findById(id).map(existingFeedback -> {
                feedback.setFeedbackId(id);
                Feedback updatedFeedback = feedbackRepository.save(feedback);
                return new ResponseEntity<>(updatedFeedback, HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete a feedback by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteFeedback(@PathVariable Long id) {
        try {
            feedbackRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete all feedbacks
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
