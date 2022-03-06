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

import com.pi.dev.models.Candidacy;
import com.pi.dev.models.Interview;
import com.pi.dev.serviceInterface.ICandidacyService;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Manage Candidacies")
@RequestMapping("/candidacy")
public class CandidacyController {
	//http://localhost:8085/SpringMVC/swagger-ui/index.html
	
	@Autowired
	ICandidacyService candidacyService;
	
	@ApiOperation(value = "Get Candidacies list")
	@GetMapping("/getAllCandidacies")
	@ResponseBody
	public List<Candidacy> getAllCandidacy() {
		return candidacyService.findAllCandidacy();
	}
	
	@ApiOperation(value = "Get Candidacies list by skill required")
	@GetMapping("/getAllCandidaciesBySkill")
	@ResponseBody
	public List<Candidacy> findAllByUserSkills(@RequestParam String skill){
		return candidacyService.findAllByUserSkills(skill);}
	
	
	@ApiOperation(value = "Add candidacy")
	@PostMapping("/addCandidacy")
	@ResponseBody
	public Candidacy addCandidacy(@RequestBody Candidacy f) {
		return candidacyService.addCandidacy(f);
	}
	
	@ApiOperation(value = "Update a Candidacy")
	@PostMapping("/updateCandidacy")
	@ResponseBody
	public Candidacy updateCandidacy(@RequestParam Long idCandidacy, @RequestBody Candidacy candidacy) {
		return candidacyService.updateCandidacy(idCandidacy, candidacy);
	}
	
	@ApiOperation(value = "Delete a Candidacy")
	@DeleteMapping("/deleteCandidacy")
	@ResponseBody
	public boolean deleteCandidacy(@RequestParam Long idCandidacy) {
		try {
			candidacyService.deleteCandidacy(idCandidacy);
			return true;
		} catch (Exception e) {
			return  false;
		}
		}
	
	@ApiOperation(value = "Assign an interview to a Candidacy")
	@PostMapping("/assignInterviewToCandidacy")
	@ResponseBody
	public boolean assignInterviewToCandidacy(@RequestParam Long idCand, @RequestBody Interview interview) {
		try {
			candidacyService.assignInterviewToCandidacy(idCand, interview);
			return true;
		} catch (Exception e) {
			return  false;
		}
	}

}
