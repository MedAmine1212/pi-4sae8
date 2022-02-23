package com.pi.dev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pi.dev.models.Contributor;
import com.pi.dev.models.JobOffer;

import com.pi.dev.serviceInterface.IJobOfferService;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Manage Job Offers")
@RequestMapping("/joboffer")
public class JobOfferController {
	//http://localhost:8085/SpringMVC/swagger-ui/index.html
	
	
	@Autowired
	IJobOfferService jobService;
	
	
	
	@ApiOperation(value = "Get Job Offers list")
	@GetMapping("/getAllJobOffers")
	@ResponseBody
	public List<JobOffer> getJobOffers() {
		return jobService.findAllJobOffers();
	}
	
	@ApiOperation(value = "Add new Job Offer")
	@PostMapping("/addJobOffer")
	@ResponseBody
	public JobOffer addJobOffer(@RequestBody JobOffer f) {
		return jobService.addJobOffer(f);
	}
	
	@ApiOperation(value = "Update a Job Offer")
	@PostMapping("/updateJobOffer")
	@ResponseBody
	public JobOffer updateJobOffer(@RequestParam Long idJobOffer, @RequestBody JobOffer job) {
		return jobService.updateJobOffer(idJobOffer, job);
	}
	
	@ApiOperation(value = "Delete a Job Offer")
	@DeleteMapping("/deleteJobOffer")
	@ResponseBody
	public boolean deleteJobOffer(@RequestParam Long idJobOffer) {
		try {
			jobService.deleteJobOffer(idJobOffer);
			return true;
		} catch (Exception e) {
			return  false;
		}
		
	}

}
