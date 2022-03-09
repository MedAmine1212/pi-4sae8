package com.pi.dev.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.pi.dev.models.Subscription;
import com.pi.dev.models.TypeSubscription;
import com.pi.dev.models.User;
import com.pi.dev.repository.SubscriptionRepository;
import com.pi.dev.repository.UserRepository;
import com.pi.dev.serviceInterface.ISubscriptionService;
import com.pi.dev.serviceInterface.IUserService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class SubscriptionServiceImpl implements ISubscriptionService{
	
	@Autowired
	SubscriptionRepository subscriptionRepository;
	@Autowired
	UserRepository userRepository;
	
	
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
	
	
	
	@Override
	public void upgradeSupscription(){
		log.info("upgradeSupscription succesful ");
		
		List<Subscription> subscriptions = subscriptionRepository.findAll();
		for(Subscription subscription : subscriptions){
		User user = subscription.getUser();


		if (subscription.getTypeSubscription().equals(TypeSubscription.Silver)&&user.getRate()>=100){
			
			subscription.setTypeSubscription(TypeSubscription.Gold);
			LocalDate date = subscription.getEndDate().plusMonths(1);
			subscription.setEndDate(date);		
			user.setRate(0);
			subscriptionRepository.save(subscription);
			userRepository.save(user);	
		}
		else
			if (subscription.getTypeSubscription().equals(TypeSubscription.Gold)&&user.getRate()>=500){	
				subscription.setTypeSubscription(TypeSubscription.Premium);
				LocalDate date = subscription.getEndDate();
				LocalDate nd= date.plusMonths(1);
				subscription.setEndDate(nd);
				user.setRate(0);
				subscriptionRepository.save(subscription);
				userRepository.save(user);
			}
			else
				if (subscription.getTypeSubscription().equals(TypeSubscription.Premium)&&user.getRate()>=1000){
					LocalDate date = subscription.getEndDate();
					LocalDate nd= date.plusMonths(1);
					subscription.setEndDate(nd);
					user.setRate(0);
					subscriptionRepository.save(subscription);
					userRepository.save(user);
				}
	}
	}
	
	
	
	//@Scheduled(fixedRate = 10000)
	public void endSubscription(Long SubscriptionID){
		log.info("endSubscription succesful ");
		Subscription subscription = new Subscription();
		subscription= subscriptionRepository.findById(SubscriptionID).get();
		if (java.time.LocalDate.now().equals(subscription.getEndDate())&& (subscription.getTypeSubscription().equals(TypeSubscription.Gold)|| subscription.getTypeSubscription().equals(TypeSubscription.Premium))){
			subscription.setTypeSubscription(TypeSubscription.Silver);	
			subscriptionRepository.save(subscription);
		}
	}
	
	
	
	
	
	public void upgradeToGold(Long SubscriptionID){
		Subscription subscription = new Subscription();
		subscription= subscriptionRepository.findById(SubscriptionID).get();
		
		if(subscription.getTypeSubscription().equals(TypeSubscription.Silver)||subscription.getTypeSubscription().equals(TypeSubscription.Premium)){
			subscription.setTypeSubscription(TypeSubscription.Gold);	
			LocalDate date = subscription.getEndDate();
			LocalDate nd= date.plusMonths(1);
			subscription.setEndDate(nd);
			subscriptionRepository.save(subscription);			

		}
		else
			if(subscription.getTypeSubscription().equals(TypeSubscription.Gold)){
				LocalDate date = subscription.getEndDate();
				LocalDate nd= date.plusMonths(1);
				subscription.setEndDate(nd);
			subscriptionRepository.save(subscription);
		}
	}
	
	

	public void upgradeToPremium(Long SubscriptionID){
		Subscription subscription = new Subscription();
		subscription= subscriptionRepository.findById(SubscriptionID).get();
		
		if(subscription.getTypeSubscription().equals(TypeSubscription.Silver)||subscription.getTypeSubscription().equals(TypeSubscription.Gold)){
			subscription.setTypeSubscription(TypeSubscription.Premium);	
			LocalDate date = subscription.getEndDate();
			LocalDate nd= date.plusMonths(1);
			subscription.setEndDate(nd);
			subscriptionRepository.save(subscription);
		}
		else
			if(subscription.getTypeSubscription().equals(TypeSubscription.Premium))
	       {
			LocalDate date = subscription.getEndDate();
			LocalDate nd= date.plusMonths(1);
			subscription.setEndDate(nd);
			subscriptionRepository.save(subscription);
		}
	}
	
}
