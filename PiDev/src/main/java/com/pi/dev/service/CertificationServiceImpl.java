package com.pi.dev.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import com.pi.dev.models.Certification;
import com.pi.dev.models.Questions;
import com.pi.dev.models.Quiz;
import com.pi.dev.models.ReputationLevel;
import com.pi.dev.models.Training;
import com.pi.dev.models.User;
import com.pi.dev.repository.CertificationRepository;
import com.pi.dev.repository.QuestionsRepository;
import com.pi.dev.repository.QuizRepository;
import com.pi.dev.repository.TrainingRepository;
import com.pi.dev.repository.UserRepository;
import com.pi.dev.serviceInterface.ICertificationService;
import com.pi.dev.serviceInterface.ITrainingService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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
        Quiz qz = tr.getQuiz();
        List<Questions> questions = qz.getQuestions();
        Certification certif = new Certification();
        for (Certification certification : crs) {
            if(certification.getTraining().equals(tr))
            {
                certif = certification;
            }
        }
        
        if((certif.getScore()*100)/questions.size()> 60 )
        {
            return certif;
        }
        return null;
  }
    @Override
    public void changeReputation(Long idUser){
        User user = ur.findById(idUser).orElse(null);
        int x =user.getReputationPoints();
        if(x<0){
            user.setReputationLevel(ReputationLevel.Hated);
        }else if(x>=0 && x<200){
            user.setReputationLevel(ReputationLevel.Neutral);

        }
        else if(x>=200 && x<800){
            user.setReputationLevel(ReputationLevel.Honorod);

        }else if(x>=800 && x<2000){
            user.setReputationLevel(ReputationLevel.Exalted);
        }

        ur.save(user);
    }

    @Override
    public String takeQuiz(Long idUser, Long idQuiz, String s) {
        Quiz qz = quir.findById(idQuiz).orElse(null);
        User user = ur.findById(idUser).orElse(null);
        Training tr = qz.getTraining();
		List<Questions> qts= qz.getQuestions();
        List<String> answrs= new ArrayList<String>();
        List<Integer> results= new ArrayList<Integer>();
        List<Integer> answrsIndexs= new ArrayList<Integer>();
        List<Certification> certifs = user.getCertif();
        Certification certif = new Certification();

        for (Certification certification : certifs) {
            if(certification.getTraining().equals(tr)){
                certif=certification;
            }
        }
        
        for (int i = 0; i < s.length(); i++) {
            char c =s.charAt(i);
            int a=Character.getNumericValue(c);  
            System.out.println(a);
            results.add(a);
        }
            for (Questions questions : qts) {
                System.out.println(questions.getLabel());
                for (String st : questions.getAnswers()) {
                    if(st.equals(questions.getPrompt())){
                        System.out.println(questions.getAnswers().indexOf(st));
                        answrsIndexs.add(questions.getAnswers().indexOf(st));
                    }
                }
               

            }
           
         int sc=0;
        for (ListIterator<Integer> iter = results.listIterator(); iter.hasNext(); ) {
            for (Integer x : answrsIndexs) {

            int et = iter.next();
            if(et==x)  {
                System.out.println("true");
                sc+=1;
            }  
            else{
                System.out.println("false");
            }
            }


        }
        int res = (sc*100)/qts.size();
        certif.setScore(res);
        if(res>=60){
            certif.setSucceeded(true);

        }else{
            certif.setSucceeded(false);
        }
        cr.save(certif);
       
        System.out.println("the results: "+ results);
        System.out.println("theindexs: "+ answrsIndexs);

        
        
        if(res>=60){
            user.setReputationPoints(user.getReputationPoints()+100);
            user.getSkills().add(tr.getSubject());
            changeReputation(idUser);
            
            return "Congratz , u ve passed the test with a score of " + res +" % , you can claim your certification now By scanning the Qr Code click below !! ";

        }
        else{
            return "unfortunately u failed at the test ";

        }
      

    }
    
}
