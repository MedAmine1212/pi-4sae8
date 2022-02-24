package com.pi.dev.serviceInterface;

import java.util.List;
import java.util.Optional;

import com.pi.dev.models.JackpotEvents;

public interface IJackpotEventService {
	
	public void addJackpotEvent (JackpotEvents JE) ;
	
	JackpotEvents updateJackpotEvent (JackpotEvents JE, Long id);
	
	boolean deleteJackpotEvent (Long id);
	
	public float showAmountEv (Long id) ; 
	
	public float showgoal (Long id) ; 
	
	List <JackpotEvents> allJackpotEvents();
	
	Optional <JackpotEvents> findJEbyid(Long id);
	
	
	
	

}
