package com.pi.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pi.dev.models.Interview;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long>{

}
