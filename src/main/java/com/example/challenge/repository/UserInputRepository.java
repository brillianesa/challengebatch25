package com.example.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInputRepository extends JpaRepository<com.example.challenge.model.UserInput, Integer>{
    
}
