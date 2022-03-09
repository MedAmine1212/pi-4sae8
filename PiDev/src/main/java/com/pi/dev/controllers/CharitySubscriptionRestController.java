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

import com.pi.dev.models.CharitySubscription;
import com.pi.dev.serviceInterface.ICharitySubscriptionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@Api(tags = "Gestion des charités")
@RequestMapping("/charitySubscription")
public class CharitySubscriptionRestController {

	
	@Autowired
	ICharitySubscriptionService csService ;
	
	
	@ApiOperation(value = "Ajouter une charité ")
	@PostMapping("/add-CS")
	@ResponseBody
	public void addCS(@RequestBody CharitySubscription Cs) {
		csService.addCS(Cs);
	}
	

	@ApiOperation(value = "Modifier une charité")
	@PutMapping("/UpdateCS/{idCharity}")
	@ResponseBody
	public CharitySubscription updateCS(@RequestBody CharitySubscription Cs, @PathVariable Long idCharity) {
		return csService.updateCS(Cs, idCharity);	
	}
	
	@ApiOperation(value = "Delete CS")
	@DeleteMapping("/deleteCS/{idCharity}")
	@ResponseBody
	public boolean deleteCS(@PathVariable Long idCharity) {
		return csService.deleteCS(idCharity);
	}
	
	@ApiOperation(value = "CSList")
	@GetMapping("/getCS")
	@ResponseBody
	public List<CharitySubscription> AllCS() {
		return csService.AllCS();
	}
	
	@ApiOperation(value = "findCSbyid")
	@GetMapping("/CS/{idCharity}")
	@ResponseBody
	public Optional<CharitySubscription> findCSbyid(Long idCharity) {
		return csService.findCSbyid(idCharity);
		
	}

	@ApiOperation(value = "calculScore")
	@PostMapping("/calculScore")
	@ResponseBody
	public void calculScore(Long idCharity) {
		csService.calculScore(idCharity);
	}

	@ApiOperation(value = "compareScore")
	@GetMapping("/TopScore/")
	@ResponseBody
	public List<CharitySubscription> TopScore() {
		 return csService.TopScore();
	}
	
	@ApiOperation(value = "filter")
	@GetMapping("/filterage/")
	@ResponseBody
	public List<CharitySubscription> allFilter(){
		return csService.allFilter();	
	}
	
	/*@ApiOperation(value = "filter by Civil Status")
	@GetMapping("/filterparCivilStatus/")
	@ResponseBody
	public List<CharitySubscription> FilterParCivilStatus(){
		return csService.FilterParCivilStatus();	
	}*/
	




	

}
