package com.pi.dev.service;

import java.util.List;

import com.pi.dev.models.Certification;
import com.pi.dev.models.Training;
import com.pi.dev.models.User;
import com.pi.dev.repository.CertificationRepository;
import com.pi.dev.repository.QuestionsRepository;
import com.pi.dev.repository.QuizRepository;
import com.pi.dev.repository.TrainingRepository;
import com.pi.dev.repository.UserRepository;
import com.pi.dev.serviceInterface.ICertificationService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class CertificationServiceImpl implements ICertificationService{

    @Autowired
	TrainingRepository qr;

	@Autowired
	QuizRepository quir;

	@Autowired
	QuestionsRepository qtsr;

	@Autowired
	UserRepository ur;

	@Autowired
	CertificationRepository cr;

    @Override
    public Certification findByIdUserTraining(Long idUser, Long idTraining) {
        User user = ur.findById(idUser).orElse(null);
		Training tr = qr.findById(idTraining).orElse(null); 
        List<Certification> crs = user.getCertif();
        for (Certification certification : crs) {
            if(certification.getTraining().equals(tr))
            {
                return certification;
            }
        }
        return null;
  }
    
}
