package com.pi.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pi.dev.models.Questions;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Long> {

}
