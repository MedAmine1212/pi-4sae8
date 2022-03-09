package com.pi.dev.serviceInterface;

import java.util.List;
import java.util.Set;

import com.pi.dev.models.Contributor;
import com.pi.dev.models.JobOffer;
import com.pi.dev.models.User;


public interface IJobOfferService {

	List<JobOffer> findAllJobOffers();
	List<JobOffer> findJobOffersByTitle(String s);
	List<JobOffer> findJobOffersBySomething(String s);
	List<JobOffer> findAllWithCookies(String s);
	List<JobOffer> findAllWithLocation();

	JobOffer addJobOffer(Long IdCotrnibutor,JobOffer f);
	
	User addJobOfferToFav(Long idJobOffer);
	boolean deleteJobOfferFromFavorites(Long idJobOffer);
	Set<JobOffer> findAllFavoriteJobs();

	boolean deleteJobOffer(Long idJobOffer);

	JobOffer updateJobOffer(Long idJobOffer, JobOffer job);

}
