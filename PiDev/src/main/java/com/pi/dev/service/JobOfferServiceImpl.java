package com.pi.dev.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.dev.models.Contributor;
import com.pi.dev.models.JobOffer;
import com.pi.dev.models.User;
import com.pi.dev.repository.JobOfferRepository;
import com.pi.dev.repository.UserRepository;
import com.pi.dev.serviceInterface.IJobOfferService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JobOfferServiceImpl implements IJobOfferService {
	
	Contributor c =new Contributor((long) 1, null, null, null, null, null, null);
	
	
	@Autowired
	JobOfferRepository jobRepo;
	@Autowired
	UserRepository userRepo;

	@Override
	public List<JobOffer> findAllJobOffers() {
		return jobRepo.findAllByOrderByJobAvailableDesc();
	}
	@Override
	public List<JobOffer> findAllWithCookies(String s) {
		return jobRepo.findAllWithCookies(s);
	}
	

	@Override
	public JobOffer addJobOffer(JobOffer f) {
		f.setContributor(c);
		  Date date = new Date();
		   f.setJobPostDate(date);
		return jobRepo.save(f);
	}

	@Override
	public JobOffer updateJobOffer(Long idJobOffer, JobOffer job) {
		
		JobOffer f=jobRepo.findById(idJobOffer).orElse(null);
		job.setJobPostDate(f.getJobPostDate());
		job.setJobExpirationDate(f.getJobExpirationDate());
		//7atta lin ya7dher contribitor dynamic
		//job.getContributor().setContributorId(f.getContributor().getContributorId()); 
		job.setJobId(idJobOffer);
		return jobRepo.save(job);
	
		
	}

	@Override
	public boolean deleteJobOffer(Long idJobOffer) {
		JobOffer f=jobRepo.findById(idJobOffer).orElse(null);
		try {
			jobRepo.delete(f);
			return true;
		} catch (Exception e) {
			return  false;
		}

		
	}



	@Override
	public List<JobOffer> findJobOffersByTitle(String s) {
		return jobRepo.findByjobNameContaining(s);
	}



	@Override
	public List<JobOffer> findJobOffersBySomething(String s) {
		return jobRepo.findBySomething(s);
	}
	
	
	@Override
	public List<JobOffer> findAllWithLocation() {
		User u =userRepo.findById((long) 1).orElse(null);
		
		List<JobOffer> jobOffersByLocations =new ArrayList<>();
		List<JobOffer> allJobs= jobRepo.findAll();
		for(JobOffer job : allJobs) {
			if(job.getLocation().equals(u.getUserLocation())){
				jobOffersByLocations.add(job);
			}
		} Set<JobOffer> filteredJobsByLocations =new LinkedHashSet<>(jobOffersByLocations);
		filteredJobsByLocations.addAll(allJobs);
		List<JobOffer> finalFoo = new ArrayList<>(filteredJobsByLocations);
		
		return finalFoo;
	}
	



}
