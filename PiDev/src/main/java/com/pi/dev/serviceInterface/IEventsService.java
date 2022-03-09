package com.pi.dev.serviceInterface;

import java.util.List;
import java.util.Optional;

import com.pi.dev.models.Events;

public interface IEventsService {

	public void addEvent (Events e); 
	
	Events updateEvent (Events e , Long idEvents);
	
	boolean deleteEvent (Long idEvents);
	
	List <Events> AllEvents() ;
	
	Optional <Events> findEventByid(Long id);
	
	public String testPlacesEvent(Long idEvents); 
	
	
	

}
