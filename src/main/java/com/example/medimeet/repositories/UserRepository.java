package com.example.medimeet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.medimeet.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}