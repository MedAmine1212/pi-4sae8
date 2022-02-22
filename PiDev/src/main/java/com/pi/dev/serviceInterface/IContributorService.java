package com.pi.dev.serviceInterface;

import java.util.List;

import com.pi.dev.models.Contributor;

public interface IContributorService {

	
	List<Contributor> findAll();

	Contributor addContributor(Contributor contributor);


	Contributor updateContributor(Contributor contributor, Long contributorID) ;
	
	void deleteContributorById(Long contributorID);
}
