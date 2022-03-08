package com.pi.dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.dev.models.CharitySubscription;
import com.pi.dev.models.Jackpots;
import com.pi.dev.repository.CharitySubscriptionRepository;
import com.pi.dev.repository.JackpotsRepository;
import com.pi.dev.serviceInterface.IJackpotsService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class JackpotsServiceImpl implements IJackpotsService {
	
	
	
	@Autowired
	JackpotsRepository JRepo ;
	
	@Autowired
	CharitySubscriptionRepository csRepo ;
	
	@Autowired
	CharitySubServiceImpl CharityService ;
	
	@Override
	public void addJackpot(Jackpots j) {
		// TODO Auto-generated method stub
		JRepo.save(j);
	}

	@Override
	public Jackpots UpdateJacpot (Jackpots j , Long idJackpot){
		// TODO Auto-generated method stub
		j.setIdJackpot(idJackpot);
		return JRepo.save(j);
	}

	@Override
	public float showAmount (Long idJackpot) {
		
		return JRepo.getById(idJackpot).getAmount();
	}

	@Override
	public List<Jackpots> AllJackpots() {
		// TODO Auto-generated method stub
		return JRepo.findAll();
	}



	@Override
	public boolean deleteJackpots(Long idJackpot) {
		// TODO Auto-generated method stub
		try {
			JRepo.deleteById(idJackpot);
			return true;
		} catch (Exception e) {
			return  false;
		}
	}

	@Override
	public Optional<Jackpots> findbyid(Long id) {
		// TODO Auto-generated method stub
		return JRepo.findById(id);
	}

	@Override
	public void calculScore(Long idJackpot) {
		
		
	}

	@Override
	public void divideAmount(Long idJackpot) {
		// TODO Auto-generated method stub
		List<CharitySubscription> chss = CharityService.allFilter() ;
		int resFilter = CharityService.allFilter().size();
		float flous = JRepo.getById(idJackpot).getAmount();
		Jackpots jr=JRepo.findById(idJackpot).orElse(null);
		float result = flous/resFilter ;
		
		for (CharitySubscription charitySubscription : chss) {
			System.out.println("MABROUK AALIK YA " + charitySubscription.getName() + " RBEHT MAANA  : " + result + " TND ") ; 
		}
		jr.setAmount(0);
		JRepo.save(jr);
		
		
	}

}
