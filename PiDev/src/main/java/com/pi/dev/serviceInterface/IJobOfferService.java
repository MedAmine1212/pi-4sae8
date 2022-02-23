package com.pi.dev.serviceInterface;

import java.util.List;

import com.pi.dev.models.Contributor;
import com.pi.dev.models.JobOffer;


public interface IJobOfferService {
	
	List<JobOffer> findAllJobOffers();
	
	JobOffer addJobOffer(JobOffer f);
	
	boolean deleteJobOffer(Long idJobOffer);

	JobOffer updateJobOffer(Long idJobOffer, JobOffer job);

}
