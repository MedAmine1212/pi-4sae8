package com.pi.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.pi.dev.models.Candidacy;

@Repository
public interface CandidacyRepository extends JpaRepository<Candidacy, Long>{

}
