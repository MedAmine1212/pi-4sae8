package com.pi.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.dev.models.Contributor;
import com.pi.dev.models.User;
import com.pi.dev.repository.ContributorRepository;
import com.pi.dev.repository.UserRepository;
import com.pi.dev.serviceInterface.IContributorService;
import com.pi.dev.serviceInterface.IRoleService;

import lombok.extern.slf4j.Slf4j;




@Service
@Slf4j
public class ContributorServiceImpl implements IContributorService {
	
	
	
	@Autowired
	ContributorRepository contributerRepository;
	
	
	
	//afficher la liste des users
	@Override
	public List<Contributor> findAll() {
		return  contributerRepository.findAll();
	}
	
	
	//ajouter un user
	@Override
	public Contributor addContributor(Contributor contributor) {
		
	return contributerRepository.save(contributor);
	}
	
	@Override
	public Contributor updateContributor(Contributor contributor, Long contributorID) {
		
		contributor.setContributorId(contributorID);
			return contributerRepository.save(contributor);
		
	}
	
	//effacer un user
	@Override
	public void deleteContributorById(Long contributorID) {
		contributerRepository.deleteById(contributorID);
		
	}
	
	
}
