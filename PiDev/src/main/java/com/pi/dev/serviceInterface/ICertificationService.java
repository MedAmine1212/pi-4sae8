package com.pi.dev.serviceInterface;

import java.util.List;

import com.pi.dev.models.Certification;
import com.pi.dev.models.Contributor;

public interface ICertificationService {

	
	Certification findByIdUserTraining(Long idUser,Long idTraining);
	String takeQuiz(Long idUser,Long idQuiz, String s);
	public void changeReputation(Long idUser);
}
