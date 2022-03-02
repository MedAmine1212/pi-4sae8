package com.pi.dev.service;

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
	InterviewRepository interviewRepo;
	
	@Autowired
	IInterviewService interviewService;
	
	

	@Override
	public List<Candidacy> findAllCandidacy() {
		return candRepo.findAll();
	}

	@Override
	public Candidacy addCandidacy(Candidacy c) {
		//User user =new User((long)1, null, null, null, null, null, null, null, null, false, null, null);
	//c.setUser(user);
	//	candRepo.save(c);
	//	f.getListCandidacyOffer().add(c);	
	//  c.setJobOffer(f);
		Date date = new Date();
		   c.setCandidacyDate(date);
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

}
