package com.pi.dev.controllers;

import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import com.pi.dev.models.Contributor;
import com.pi.dev.models.JobOffer;
import com.pi.dev.models.User;
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
	public List<JobOffer> getJobOffers(HttpServletRequest req) {
		if(WebUtils.getCookie( req , "joboffercookies")==null) {
		return jobService.findAllWithLocation();}
		else {String[] arrOfStr = WebUtils.getCookie( req , "joboffercookies").getValue().split("--");
		String c ="";
        for (String a : arrOfStr) {
        	
           a.replaceAll("&", " ") ;
           c =a ;}
			
			return jobService.findAllWithCookies(c);}
	}
	
	@ApiOperation(value = "Get Favorite JobOffers")
	@GetMapping("/getAllFavoriteJobOffers")
	@ResponseBody
	public Set<JobOffer> findAllFavoriteJobs() {
		return jobService.findAllFavoriteJobs();
	}
	
	@ApiOperation(value = "Add new Job Offer")
	@PostMapping("/addJobOffer/{contributor-id}")
	@ResponseBody
	public JobOffer addJobOffer(@PathVariable("contributor-id")Long contributerId ,@RequestBody JobOffer f) {
		return jobService.addJobOffer(contributerId, f);
	}
	
	@ApiOperation(value = "Add Job Offer to favorites")
	@PostMapping("/addJobOfferToFavorites")
	@ResponseBody
	public User addJobOfferToFav(@RequestParam Long idJobOffer) {
		return jobService.addJobOfferToFav(idJobOffer);
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
	
	@ApiOperation(value = "Delete a Job Offer From Favorites")
	@DeleteMapping("/deleteJobOfferFromFavorites")
	@ResponseBody
	public boolean deleteJobOfferFromFavorites(@RequestParam Long idJobOffer) {
		try {
			jobService.deleteJobOfferFromFavorites(idJobOffer);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return  false;
		}
		
	}
	
	
	
	@ApiOperation(value = "Get Job Offers list by name")
	@GetMapping("/getJobOfferByName")
	@ResponseBody
	public List<JobOffer> findJobOffersByTitle(@RequestParam String name){
		return jobService.findJobOffersByTitle(name);
	}
	
	@ApiOperation(value = "Get Job Offers list by something")
	@GetMapping("/getJobOfferBySomething")
	@ResponseBody
	public List<JobOffer> findJobOffersBySomething(@RequestParam String something,HttpServletResponse response ,HttpServletRequest req ){
		if (WebUtils.getCookie( req , "joboffercookies") == null) {
			Cookie oldcookie =new Cookie("joboffercookies", "");
			Cookie cookie =new Cookie("joboffercookies", something.replaceAll("\\s", "&"));
			cookie.setMaxAge(60*60*24);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		else {
			Cookie oldcookie =WebUtils.getCookie( req , "joboffercookies");
		Cookie cookie =new Cookie("joboffercookies", something.replaceAll("\\s", "&")+"--"+ oldcookie.getValue());
		cookie.setMaxAge(60*60*24);
		cookie.setPath("/");
		response.addCookie(cookie); }
		return jobService.findJobOffersBySomething(something);
	}

}
