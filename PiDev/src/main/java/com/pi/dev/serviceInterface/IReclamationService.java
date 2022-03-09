package com.pi.dev.serviceInterface;

import java.util.List;

import com.pi.dev.models.Reclamation;

public interface IReclamationService {
    List<Reclamation> listReclamations();
    Reclamation addReclamation (Reclamation reclamation);
    boolean deleteReclamation(Long IdReclamation);
}
