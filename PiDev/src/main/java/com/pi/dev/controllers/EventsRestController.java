package com.pi.dev.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pi.dev.models.Events;
import com.pi.dev.serviceInterface.IEventsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Gestion des evenements")
@RequestMapping("/evenement")

public class EventsRestController {

	@Autowired
	IEventsService EvService ;
	
	@ApiOperation(value = "Ajouter un evenement")
	@PostMapping("/add-events")
	@ResponseBody
	public void addEvent(@RequestBody Events e) {
		EvService.addEvent(e); 
	}
	
	
	@ApiOperation(value = "Modifier un evenement")
	@PutMapping("/UpdateEvents/{idEvents}")
	@ResponseBody
	public Events updateEvent(@RequestBody Events e, @PathVariable Long idEvents) {
		return EvService.updateEvent(e, idEvents);
	}
	
	
	@ApiOperation(value = "Delete Event")
	@DeleteMapping("/deleteEvents/{idEvents}")
	@ResponseBody
	public boolean deleteEvent(@PathVariable Long idEvents) {
		return EvService.deleteEvent(idEvents);
	}
	
	@ApiOperation(value = "EventsList")
	@GetMapping("/getEvents")
	@ResponseBody
	public List<Events> AllEvents() {
		return EvService.AllEvents();
		
	}
	@ApiOperation(value = "GetEventsByid")
	@GetMapping("/Event/{id}")
	@ResponseBody
	public Optional<Events> findEventByid(@PathVariable Long id) {
		return EvService.findEventByid(id);
	}



		
}
