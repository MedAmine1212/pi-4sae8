package com.pi.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pi.dev.models.JobOffer;


@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {

}
