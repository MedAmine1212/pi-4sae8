package com.pi.dev.service;

import java.util.List;

import com.pi.dev.models.Reclamation;
import com.pi.dev.repository.ReclamationRepository;
import com.pi.dev.serviceInterface.IReclamationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReclamationService implements IReclamationService {
    @Autowired
    ReclamationRepository rr;
    @Override
    public List<Reclamation> listReclamations() {
        return rr.findAll();
        
    }
    @Override
    public Reclamation addReclamation(Reclamation reclamation) {
        
        return rr.save(reclamation);
    }
    public boolean deleteReclamation(Long IdReclamation) {
		try {
			rr.deleteById(IdReclamation);
			return true;
		} catch (Exception e) {
			return  false;
		}
	}

    
}
