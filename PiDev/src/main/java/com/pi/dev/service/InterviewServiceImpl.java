package com.pi.dev.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.dev.models.Interview;

import com.pi.dev.repository.InterviewRepository;

import com.pi.dev.serviceInterface.IInterviewService;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InterviewServiceImpl implements IInterviewService{
	@Autowired
	InterviewRepository interviewRepo;
	
	@Override
	public List<Interview> findAllInterview() {
		return interviewRepo.findAll();
	}

	@Override
	public Interview addInterview(Interview f) {
		
		  Date date = new Date();
		  
		   
		return interviewRepo.save(f);
	}

	@Override
	public Interview updateInterview(Long idInterview, Interview inter) {
		
		Interview f=interviewRepo.findById(idInterview).orElse(null);
		
		inter.setInterviewId(idInterview);
		return interviewRepo.save(inter);
	
		
	}

	@Override
	public boolean deleteInterview(Long idInterview) {
		Interview f=interviewRepo.findById(idInterview).orElse(null);
		try {
			interviewRepo.delete(f);
			return true;
		} catch (Exception e) {
			return  false;
		}

		
	}
	
	

}
