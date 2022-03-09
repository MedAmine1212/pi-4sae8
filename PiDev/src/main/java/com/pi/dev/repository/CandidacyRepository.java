package com.pi.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.pi.dev.models.Candidacy;
import com.pi.dev.models.JobOffer;

@Repository
public interface CandidacyRepository extends JpaRepository<Candidacy, Long>{
 
	@Query(value = "SELECT * FROM candidacy c WHERE c.job_name LIKE %:word%", nativeQuery = true)
	List<Candidacy> findBySomething(@Param("word") String s);
}
