package com.pi.dev.serviceInterface;

import java.util.List;
import java.util.Optional;

import com.pi.dev.models.CharitySubscription;


public interface ICharitySubscriptionService {
	
	public void addCS (CharitySubscription Cs );
	
	CharitySubscription updateCS (CharitySubscription Cs , Long idCharity);
	
	boolean deleteCS (Long idCharity);
	
	List<CharitySubscription> AllCS();
	
	Optional <CharitySubscription> findCSbyid (Long idCharity);
	
	public void calculScore ( Long idCharity);
	
	public void CompareScore();
	

}
