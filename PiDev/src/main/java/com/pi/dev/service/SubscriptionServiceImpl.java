package com.pi.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.dev.models.Subscription;
import com.pi.dev.repository.SubscriptionRepository;
import com.pi.dev.serviceInterface.ISubscriptionService;
import com.pi.dev.serviceInterface.IUserService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class SubscriptionServiceImpl implements ISubscriptionService{
	
	@Autowired
	SubscriptionRepository subscriptionRepository;
	
	
	
	//afficher la liste des users
	@Override
	public List<Subscription> findAll() {
		return  subscriptionRepository.findAll();
	}
	
	
	//ajouter un user
	@Override
	public Subscription addSubscription(Subscription subscription) {
		
	return subscriptionRepository.save(subscription);
	}
	
	@Override
	public Subscription updateSubscription(Subscription subscription, Long SubscriptionID) {
			subscription.setSubscriptionId(SubscriptionID);
			return subscriptionRepository.save(subscription);
		
	}
	
	
	//effacer un user
	@Override
	public void deleteSubscriptionById(Long SubscriptionID) {
		subscriptionRepository.deleteById(SubscriptionID);
		
	}
	
	
}
