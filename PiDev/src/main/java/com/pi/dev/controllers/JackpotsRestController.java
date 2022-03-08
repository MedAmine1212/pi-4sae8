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

import com.pi.dev.models.Jackpots;
import com.pi.dev.serviceInterface.IJackpotsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@Api(tags = "Gestion des cagnottes")
@RequestMapping("/cagnotte")
public class JackpotsRestController {
	
	@Autowired
	IJackpotsService JService ;
	
    //http://localhost:8087/SpringMVC/swagger-ui/index.html

	
	@ApiOperation(value = "Ajouter une cagnotte")
	@PostMapping("/add-cagnotte")
	@ResponseBody
	public void addJackpot (@RequestBody Jackpots j){
		JService.addJackpot(j);
	}
	
	@ApiOperation(value = "modifier une cagnotte")
	@PutMapping("/UpdateCagnotte/{idJackpot}")
	@ResponseBody
	Jackpots UpdateJacpot (@RequestBody Jackpots j , @PathVariable Long idJackpot){
		return JService.UpdateJacpot(j, idJackpot);
	}

	@ApiOperation(value = "affichage le montant de cagnotte")
	@GetMapping("/getAmont/{idJackpot}")
	@ResponseBody
	public float showAmount (@PathVariable Long idJackpot){
		return JService.showAmount(idJackpot);
	}
	
	@ApiOperation(value = "Get All jackpots")
	@GetMapping("/getJackpots")
	@ResponseBody
	public List<Jackpots> AllJackpots(){
		return JService.AllJackpots();
		
	}
	/*@ApiOperation(value = "Get one jackpots")
	@GetMapping("/oneJackpots")
	@ResponseBody
	public Optional<Jackpots> OneJackpots(@PathVariable Long idJackpot){
		return JService.OneJackpots(idJackpot);
	}*/
	
	@ApiOperation(value = "Get one jackpots")
	@GetMapping("/oneJackpots/{id}")
	@ResponseBody
	public Optional<Jackpots> findbyid(@PathVariable Long id) {
		return JService.findbyid(id);
	}
	
	@ApiOperation(value = "Delete jackpots")
	@DeleteMapping("/deletejackpots/{idJackpot}")
	@ResponseBody
	public boolean deleteJackpots(@PathVariable Long idJackpot){
		return JService.deleteJackpots(idJackpot);
		
	}

}
