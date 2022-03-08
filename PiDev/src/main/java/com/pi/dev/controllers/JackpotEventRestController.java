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

import com.pi.dev.models.JackpotEvents;
import com.pi.dev.serviceInterface.IJackpotEventService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@Api(tags = "Gestion des cagnottes des evenements")
@RequestMapping("/cagnotte_evenement")
public class JackpotEventRestController {

	@Autowired
	IJackpotEventService jeService ;
	
	
	@ApiOperation(value = "Ajouter une cagnotte d'evenement")
	@PostMapping("/add-cagnotte-evenement")
	@ResponseBody
	public void addJackpotEvent(@RequestBody JackpotEvents JE) {
		jeService.addJackpotEvent(JE);
	}
	
	@ApiOperation(value = "modifier une cagnotte d'evenement")
	@PutMapping("/UpdateCagnotteEvenement/{id}")
	@ResponseBody
	public JackpotEvents updateJackpotEvent(@RequestBody JackpotEvents JE, @PathVariable Long id) {
		return jeService.updateJackpotEvent(JE, id);
	}
	
	@ApiOperation(value = "supression d'une cagnotte d'evenement")
	@DeleteMapping("/deletejackpotEvenement/{id}")
	@ResponseBody
	public boolean deleteJackpotEvent(@PathVariable Long id) {
		return jeService.deleteJackpotEvent(id);
	}
	
	@ApiOperation(value = "affichage le montant de cagnotte")
	@GetMapping("/getAmont/{id}")
	@ResponseBody
	public float showAmountEv(@PathVariable Long id) {
		return jeService.showAmountEv(id);
	}
	
	@ApiOperation(value = "affichage de but d'argent a collecter")
	@GetMapping("/getGoal/{id}")
	@ResponseBody
	public float showgoal(@PathVariable Long id) {
		return jeService.showgoal(id);
	}
	
	@ApiOperation(value = "Get All jackpotEvent")
	@GetMapping("/getJackpotEvents")
	@ResponseBody
	public List<JackpotEvents> allJackpotEvents() {
		return jeService.allJackpotEvents();
	}
	
	@ApiOperation(value = "Get one jackpotEvent")
	@GetMapping("/oneJackpotevent/{id}")
	@ResponseBody
	public Optional<JackpotEvents> findJEbyid(Long id) {
		return jeService.findJEbyid(id);
	}







}
