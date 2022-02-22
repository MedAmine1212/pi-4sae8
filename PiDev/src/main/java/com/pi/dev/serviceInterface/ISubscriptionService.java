package com.pi.dev.serviceInterface;

import java.util.List;

import com.pi.dev.models.Subscription;

public interface ISubscriptionService {

	
	List<Subscription> findAll();

	Subscription addSubscription(Subscription subscription);

	Subscription updateSubscription(Subscription subscription, Long SubscriptionID);

	void deleteSubscriptionById(Long SubscriptionID);
}
