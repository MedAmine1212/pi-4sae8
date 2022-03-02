package com.pi.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pi.dev.models.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

}
