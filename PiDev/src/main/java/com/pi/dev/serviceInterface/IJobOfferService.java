package com.pi.dev.serviceInterface;

import java.util.List;

import com.pi.dev.models.Contributor;
import com.pi.dev.models.JobOffer;


public interface IJobOfferService {
	
	List<JobOffer> findAllJobOffers();
	List<JobOffer> findJobOffersByTitle(String s);
    List<JobOffer> findJobOffersBySomething(String s);
    List<JobOffer> findAllWithCookies(String s);
	
	JobOffer addJobOffer(JobOffer f);
	
	boolean deleteJobOffer(Long idJobOffer);

	JobOffer updateJobOffer(Long idJobOffer, JobOffer job);

}
