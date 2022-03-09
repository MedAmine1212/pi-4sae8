package com.pi.dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.dev.models.Events;
import com.pi.dev.models.JackpotEvents;
import com.pi.dev.repository.EventsRepository;
import com.pi.dev.repository.JackpotEventRepository;
import com.pi.dev.serviceInterface.IEventsService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EventsServiceImpl implements IEventsService {
	
	
	@Autowired
	EventsRepository EvRepo ;
	
	@Autowired
	JackpotEventRepository JeRepo ;
	
	@Override
	public void addEvent(Events e) {
		// TODO Auto-generated method stub
		
		JackpotEvents jckE = new JackpotEvents();
		e.setJackpotEvents(jckE);
		jckE.setEvents(e);
		EvRepo.save(e);
	}

	@Override
	public Events updateEvent(Events e, Long idEvents) {
		// TODO Auto-generated method stub
		e.setIdEvents(idEvents);
		return EvRepo.save(e);
	}

	@Override
	public boolean deleteEvent(Long idEvents) {
		// TODO Auto-generated method stub
		try {
			EvRepo.deleteById(idEvents);	
			return true ;
		}catch (Exception e) {
			return  false;
		}
	}

	@Override
	public List<Events> AllEvents() {
		// TODO Auto-generated method stub
		return EvRepo.findAll();
	}

	@Override
	public Optional<Events> findEventByid(Long id) {
		// TODO Auto-generated method stub
		return EvRepo.findById(id);
	}

	@Override
	public String testPlacesEvent(Long idEvents) {
		// TODO Auto-generated method stub
		Events e = EvRepo.getById(idEvents);
	
		int NP = e.getNbrParticipant() ;
		int NPMax = e.getMaxNbrParticipant() ;
		float rate = 0 ;
		
		//hawel badelha b case 
		if (e.getMaxNbrParticipant() - e.getNbrParticipant() == 0){
			rate = 100 ;
		}else 
		
			rate = NP * 100 / NPMax ;
		
		/*if ( e.getMaxNbrParticipant() - e.getNbrParticipant() > 75 ){
			rate = rate 			
		}*/
		/*
		if (NPMax - NP > 50 && NPMax - NP <= 75 ){
			rate = rate + 75 ;
			return ("Rate is successful");
		}
		
		if (NPMax - NP > 75 && NPMax - NP <= 100 ){
			rate = rate + 100 ;
			return ("Rate is Very successful");
		}
		*/
		
		e.setRatiing(rate);
		
		EvRepo.save(e);
		
		return ("Rating for this event is " + rate );
	
	}
	
	
	
	
}

	
	
