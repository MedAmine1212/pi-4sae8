package com.pi.dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.dev.models.CharitySubscription;
import com.pi.dev.repository.CharitySubscriptionRepository;
import com.pi.dev.serviceInterface.ICharitySubscriptionService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CharitySubscriptionServiceImpl implements ICharitySubscriptionService {
	
	@Autowired
	CharitySubscriptionRepository CSubRepo ;
	
	
	@Override
	public void addCS(CharitySubscription Cs) {
		// TODO Auto-generated method stub
		CSubRepo.save(Cs);
		
	}

	@Override
	public CharitySubscription updateCS(CharitySubscription Cs, Long idCharity) {
		// TODO Auto-generated method stub
		Cs.setIdCharity(idCharity);
		return CSubRepo.save(Cs);
	}

	@Override
	public boolean deleteCS(Long idCharity) {
		// TODO Auto-generated method stub
		try {
			CSubRepo.deleteById(idCharity);
			return true;
		}catch (Exception e) {
			return  false;
		}
		
	}

	@Override
	public List<CharitySubscription> AllCS() {
		// TODO Auto-generated method stub
		return CSubRepo.findAll();
	}

	@Override
	public Optional<CharitySubscription> findCSbyid(Long idCharity) {
		// TODO Auto-generated method stub
		return CSubRepo.findById(idCharity);
	}

}
