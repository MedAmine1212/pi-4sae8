package com.pi.dev.service;

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
	
	
	
	
	@Scheduled(cron = "0 0 * * * *")
	public void upgradeSupscription(Long SubscriptionID){
	
		Subscription subscription = new Subscription();
		subscription= subscriptionRepository.findById(SubscriptionID).get();
		User user = subscription.getUser();


		if (subscription.getTypeSubscription().equals("Silver")&&user.getRate()==100){
			
			subscription.setTypeSubscription(TypeSubscription.Gold);
			Date date = subscription.getEndDate();
			int month =date.getMonth();
			month++;
			date.setMonth(month);
			subscription.setEndDate(date);			
			user.setRate(0);
			subscriptionRepository.save(subscription);
			userRepository.save(user);

			
		}
		else
			if (subscription.getTypeSubscription().equals("Gold")&&user.getRate()==500){
				
				subscription.setTypeSubscription(TypeSubscription.Premium);
				Date date = subscription.getEndDate();
				int month =date.getMonth();
				month++;
				date.setMonth(month);
				subscription.setEndDate(date);
				user.setRate(0);
				subscriptionRepository.save(subscription);
				userRepository.save(user);

				
			}
			else
				if (subscription.getTypeSubscription().equals("Premium")&&user.getRate()==1000){
					Date date = subscription.getEndDate();
					int month =date.getMonth();
					month++;
					date.setMonth(month);
					subscription.setEndDate(date);
					user.setRate(0);
					subscriptionRepository.save(subscription);
					userRepository.save(user);

				}
	}
	
	
	
	
	public void EndSubscription(Long SubscriptionID){
		Subscription subscription = new Subscription();
		subscription= subscriptionRepository.findById(SubscriptionID).get();
		if (java.time.LocalDate.now().equals(subscription.getEndDate())&& subscription.getTypeSubscription().equals("Silver") ){
			subscription.setTypeSubscription(TypeSubscription.Silver);	
			subscriptionRepository.save(subscription);
		}
	}
	
	
	
	
	public void UpgradeToGold(Long SubscriptionID){
		Subscription subscription = new Subscription();
		subscription= subscriptionRepository.findById(SubscriptionID).get();
		
		if(subscription.getTypeSubscription().equals("Silver")||subscription.getTypeSubscription().equals("Premium")){
			subscription.setTypeSubscription(TypeSubscription.Gold);	
			subscriptionRepository.save(subscription);			
		}
		else{
			Date date = subscription.getEndDate();
			int month =date.getMonth();
			month++;
			date.setMonth(month);
			subscription.setEndDate(date);
			subscriptionRepository.save(subscription);
		}
	}
	
	
	
	
	
	
	public void UpgradeToPremium(Long SubscriptionID){
		Subscription subscription = new Subscription();
		subscription= subscriptionRepository.findById(SubscriptionID).get();
		
		if(subscription.getTypeSubscription().equals("Silver")||subscription.getTypeSubscription().equals("Gold")){
			subscription.setTypeSubscription(TypeSubscription.Premium);	
			subscriptionRepository.save(subscription);			
		}
		else{
			Date date = subscription.getEndDate();
			int month =date.getMonth();
			month++;
			date.setMonth(month);
			subscription.setEndDate(date);
			subscriptionRepository.save(subscription);
		}
	}
	
}
