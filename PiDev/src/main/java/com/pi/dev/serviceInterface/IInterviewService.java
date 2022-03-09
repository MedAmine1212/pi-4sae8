package com.pi.dev.serviceInterface;

import java.util.List;

import com.pi.dev.models.Interview;

public interface IInterviewService {

	
List<Interview> findAllInterview();
	
	Interview addInterview(Interview c);
	
	boolean deleteInterview(Long idInterview);

	Interview updateInterview(Long idInterview, Interview can);
}
