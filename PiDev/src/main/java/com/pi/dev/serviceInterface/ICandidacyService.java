package com.pi.dev.serviceInterface;

import java.util.List;

import com.pi.dev.models.Candidacy;
import com.pi.dev.models.Interview;

public interface ICandidacyService {

   List<Candidacy> findAllCandidacy();
	
	Candidacy addCandidacy(Candidacy c);
	
	boolean deleteCandidacy(Long idCandidacy);

	Candidacy updateCandidacy(Long idCandidacy, Candidacy can);
	
	boolean assignInterviewToCandidacy(Long c , Interview i);
}
