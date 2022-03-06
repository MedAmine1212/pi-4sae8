package com.pi.dev.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pi.dev.models.JobOffer;


@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {


	List<JobOffer> findAllByOrderByLocationAsc();
	List<JobOffer> findAllByOrderByJobAvailableDesc();


	List<JobOffer> findByjobNameContaining(String s);

	@Query(value = "(SELECT * FROM job_offer) UNION (SELECT * FROM job_offer WHERE job_offer.job_name LIKE %:word% OR job_offer.job_description LIKE %:word%) ORDER BY job_name desc ,job_description desc", nativeQuery = true)
	List<JobOffer> findAllWithCookies(@Param("word") String word);

	@Query(value = "SELECT * FROM job_offer WHERE job_offer.job_name LIKE %:word% OR job_offer.job_description LIKE %:word%", nativeQuery = true)
	List<JobOffer> findBySomething(@Param("word") String s);
	
	


}
