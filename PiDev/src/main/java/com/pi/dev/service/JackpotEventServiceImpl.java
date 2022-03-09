package com.pi.dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.dev.models.JackpotEvents;
import com.pi.dev.repository.JackpotEventRepository;
import com.pi.dev.serviceInterface.IJackpotEventService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class JackpotEventServiceImpl implements IJackpotEventService {
	
	@Autowired
	JackpotEventRepository jeRepo ;
	
	@Override
	public void addJackpotEvent(JackpotEvents JE) {
		// TODO Auto-generated method stub
		jeRepo.save(JE);
		
	}

	@Override
	public JackpotEvents updateJackpotEvent(JackpotEvents JE, Long id) {
		// TODO Auto-generated method stub
		JE.setId(id);
		return jeRepo.save(JE);
	}

	@Override
	public boolean deleteJackpotEvent(Long id) {
		// TODO Auto-generated method stub
		try{
			jeRepo.deleteById(id);
			return true;
		}catch (Exception e) {
			return  false;
		}
	}

	@Override
	public float showAmountEv(Long id) {
		// TODO Auto-generated method stub
		return jeRepo.getById(id).getMontant();
	}

	@Override
	public float showgoal(Long id) {
		// TODO Auto-generated method stub
		return jeRepo.getById(id).getGoal();
	}

	@Override
	public List<JackpotEvents> allJackpotEvents() {
		// TODO Auto-generated method stub
		return jeRepo.findAll();
	}

	@Override
	public Optional<JackpotEvents> findJEbyid(Long id) {
		// TODO Auto-generated method stub
		return jeRepo.findById(id);
	}
	
	

}
