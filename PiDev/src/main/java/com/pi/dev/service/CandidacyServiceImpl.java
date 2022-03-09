package com.pi.dev.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.dev.models.Candidacy;
import com.pi.dev.models.Contributor;
import com.pi.dev.models.Interview;
import com.pi.dev.models.JobOffer;
import com.pi.dev.models.User;
import com.pi.dev.repository.CandidacyRepository;
import com.pi.dev.repository.InterviewRepository;
import com.pi.dev.repository.JobOfferRepository;
import com.pi.dev.repository.UserRepository;
import com.pi.dev.serviceInterface.ICandidacyService;
import com.pi.dev.serviceInterface.IInterviewService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CandidacyServiceImpl implements ICandidacyService {
	//JobOffer offer=new JobOffer((long)1, "HARD CODED", null, null, null, 0, null, null);
	
	
	@Autowired
	CandidacyRepository candRepo;
	
	@Autowired
	JobOfferRepository jobRepo;
	@Autowired
	
	UserRepository userRepo;
	
	@Autowired
	InterviewRepository interviewRepo;
	
	@Autowired
	IInterviewService interviewService;
	
	

	@Override
	public List<Candidacy> findAllCandidacy() {
		return candRepo.findAll();
	}
	
	
	
	
	@Override
	public List<Candidacy> findAllByUserSkills(String skill) {
		List<Candidacy> candBySkill =  new ArrayList<>();
		List<Candidacy> allCand =candRepo.findAll();
		
		for(Candidacy candidacy : allCand ) {
			List<String> skills =candidacy.getUser().getSkills();
			log.info(""+candidacy);
			for(String s : skills) {
				
				if(s.equalsIgnoreCase(skill)) {
					candBySkill.add(candidacy);
					
				}
			}
			
			
		}
		return candBySkill;
	}
	
	

	@Override
	public Candidacy addCandidacy(Candidacy c, Long idJob) {
		User u =userRepo.findById((long) 1).orElse(null);
		JobOffer job=jobRepo.findById(idJob).orElse(null);
	
		candRepo.save(c);
		Date date = new Date();
		   c.setCandidacyDate(date);
		   c.setUser(u);
		   c.setJobOffer(job);
		   u.getListCandidacyUser().add(c);
		   userRepo.save(u);
		   candRepo.save(c);
		return candRepo.save(c);
	}

	@Override
	public boolean deleteCandidacy(Long idCandidacy) {
		Candidacy oldcand=candRepo.findById(idCandidacy).orElse(null);
		try {
			candRepo.deleteById(idCandidacy);
			return true;
		} catch (Exception e) {
			return  false;
	}}

	@Override
	public Candidacy updateCandidacy(Long idCandidacy, Candidacy cand) {
		Candidacy oldcand=candRepo.findById(idCandidacy).orElse(null);
		
		cand.setCandidacyDate(oldcand.getCandidacyDate());
		cand.setCandidacyId(idCandidacy);
		return candRepo.save(cand);
	}

	@Override
	public boolean assignInterviewToCandidacy(Long idCand, Interview i) {
		
		Candidacy c =candRepo.findById(idCand).orElse(null);
		
		if(c.getCandidacyStatus()==2) {
			Interview inter = interviewService.addInterview(i);
			interviewRepo.save(inter);
			c.setInterview(inter);
			inter.setCandidacy(c);
			interviewRepo.save(inter);
			candRepo.save(c);
			return true;
		}else
		return false;
	}




	@Override
	public List<Candidacy> findAllByUser() {
		User u =userRepo.findById((long) 1).orElse(null);
		
		return u.getListCandidacyUser();
	}

	

}
