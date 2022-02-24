package com.pi.dev.serviceInterface;

import java.util.List;
import java.util.Optional;

import com.pi.dev.models.Jackpots;

public interface IJackpotsService {

	public void addJackpot (Jackpots j) ;
	
	Jackpots UpdateJacpot (Jackpots j , Long idJackpot);
	
	public float showAmount (Long idJackpot) ; 
	
	List <Jackpots> AllJackpots() ;
	
	Optional <Jackpots> findbyid(Long id);
	 
	boolean deleteJackpots (Long idJackpot) ;

}
