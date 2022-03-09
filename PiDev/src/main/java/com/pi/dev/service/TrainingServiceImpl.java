package com.pi.dev.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.dev.models.Certification;
import com.pi.dev.models.Questions;
import com.pi.dev.models.Quiz;
import com.pi.dev.models.ReputationLevel;
import com.pi.dev.models.Trainer;
import com.pi.dev.models.Training;
import com.pi.dev.models.User;
import com.pi.dev.repository.CertificationRepository;
import com.pi.dev.repository.QuestionsRepository;
import com.pi.dev.repository.QuizRepository;
import com.pi.dev.repository.TrainingRepository;
import com.pi.dev.repository.UserRepository;
import com.pi.dev.serviceInterface.ITrainingService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TrainingServiceImpl implements ITrainingService {

	@Autowired
	TrainingRepository qr;

	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	QuizRepository quir;

	@Autowired
	QuestionsRepository qtsr;

	@Autowired
	UserRepository ur;

	@Autowired
	CertificationRepository cr;

	@Override
	public List<Training> findAll() {
		// TODO Auto-generated method stub
		return qr.findAll();
	}

	@Override
	public Training addTraining(Training Training) {
		// TODO Auto-generated method stub
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		log.info(formatter.format(date));
		if (Training.getStartDate().compareTo(date) > 0 && Training.getEndDate().compareTo(date) > 0) {
			if (Training.getEndDate().compareTo(Training.getStartDate()) > 0)
				return qr.save(Training);
		} else {
			log.info("day of training should be after today");
		}
		return null;

	}

	@Override
	public Training updateTraining(Training Training, Long trainingId) {
		Training.setTrainingId(trainingId);
		return qr.save(Training);
	}

	@Override
	public void deleteTraining(Training q) {
		// TODO Auto-generated method stub
		qr.delete(q);
	}

	@Override
	public void deleteTrainingById(Long trainingId) {
		// TODO Auto-generated method stub
		qr.deleteById(trainingId);
	}

	@Override
	public Training getTraining(Long trainingId) {
		// TODO Auto-generated method stub
		return qr.findById(trainingId).orElse(null);
	}

	@Override
	public void assignQuizToTraining(Long trainingId, Quiz qz) {
		Training tr = qr.findById(trainingId).orElse(null);
		List<Questions> questions = (List<Questions>) qz.getQuestions();
		qtsr.saveAll(questions);
		for (Questions question : questions) {
			question.setQuiz(qz);
		}
		Quiz qtz = quir.save(qz);
		tr.setQuiz(qtz);
		qr.save(tr);
	}

	@Override
	public void ParticipateToTraining(Long userId, Long trainingId) {
		// TODO Auto-generated method stub
		Training tr = qr.findById(trainingId).orElse(null);
		Training trjoined = new Training();

		User user = ur.findById(userId).orElse(null);
		Certification certif = new Certification();
		List<Certification> certifs = user.getCertif();
		Certification certifjoined = new Certification();

		for (Certification certification : certifs) {
			if(certification.getTraining().equals(tr)){
				trjoined=certification.getTraining();
			}
		}
		


		if(!user.getReputationLevel().equals(ReputationLevel.Hated) ){
			if(tr.equals(trjoined)){
				System.out.println("Already Joined");

			}else{
				certif.setTraining(tr);
				certif.setUser(user);
				certif.setSucceeded(false);
				cr.save(certif);
				tr.setMaxNbrParticipants(tr.getMaxNbrParticipants() + 1);
				qr.save(tr);
			}
		
		}
		else
		{
			System.out.println("you r being suspended from joining training because of bad reputation");
		}
		
	}
    
	@Override
	public void AnnulerParticipation(Long userId, Long trainingId) {
		// TODO Auto-generated method stub
		User user = ur.findById(userId).orElse(null);
		Training tr = qr.findById(trainingId).orElse(null);

		List<Certification> usercertif = user.getCertif();

		for (Certification certification : usercertif) {
			if (certification.getTraining().equals(tr)) {
				cr.deleteById(certification.getId());
			}
		}
		tr.setMaxNbrParticipants(tr.getMaxNbrParticipants() - 1);
		qr.save(tr);

	}



	@Override
	public List<Training> findbetweendates(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return qr.retrieveTrainingsBetWeenDates(startDate, endDate);
	}

	@Override
	public List<Training> getTrainingBySubject(Long userId,String s) {
		User user = ur.findById(userId).orElse(null);
		List<Training> trs = qr.findAll();
		List<Training> subjcts = new ArrayList<Training>();
		for (Training training : trs) {
			if (training.getSubject().contains(s)) {
				subjcts.add(training);
			} else {
				log.info("no training with this subject");
			}
		}
		List<String> searchs = user.getSearchHistory();
		searchs.add(s);
		ur.save(user);
		return subjcts;
	}
	@Override
	public List<Training> recommendedTraining(Long idUser) {
		User user = ur.findById(idUser).orElse(null);
		List<Training> alltrainings = qr.findAll();
		List<Certification> crs = user.getCertif();
		List<Training> trs = new ArrayList<Training>();
		List<Training> recommendedTrainings = new ArrayList<Training>();

		Map<String, Integer> recmds = new LinkedHashMap<String, Integer>();

		for (Certification certif : crs) {
			trs.add(certif.getTraining());
		}
		alltrainings.removeAll(trs);

		for (Training training : trs) {
			String[] arrOfStr = training.getSubject().split(" ", 5);
			for (String a : arrOfStr) {
				if (a.length() > 3) {
					if (!recmds.containsKey(a)) {
						recmds.put(a, 1);
					} else {
						recmds.put(a, recmds.get(a) + 1);
					}
				}
			}
		}
		List<String> arrOfsubjects = new ArrayList<String>();

		for (Map.Entry<String, Integer> me : recmds.entrySet()) {
			if (me.getValue() > 2) {
				arrOfsubjects.add(me.getKey());
			}
		}
		for (Training train : alltrainings) {

			for (String st : arrOfsubjects) {
				if (train.getSubject().contains(st)) {
					recommendedTrainings.add(train);
				}
			}
		}
		
		return recommendedTrainings;
	}
	public static Map<String, Integer> sortByValue(final Map<String, Integer> wordCounts) {

        return wordCounts.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed())).limit(1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

	@Override
	public List<Training> recommendedTrainingBySearchs(Long idUser) {
		User user = ur.findById(idUser).orElse(null);
		List<String> searchs = user.getSearchHistory();
		List<Training> alltrainings = qr.findAll();
		List<Training> recommendedTrainings = new ArrayList<Training>();
		Map<String, Integer> recmds = new LinkedHashMap<String, Integer>();
		
		for (String sear : searchs) {
			String[] arrOfStr = sear.split(" ", 5);
			for (String a : arrOfStr) {
				if (a.length() > 3) {
					if (!recmds.containsKey(a)) {
						recmds.put(a, 1);
					} else {
						recmds.put(a, recmds.get(a) + 1);
					}
				}
			}
		}
		
		List<String> arrOfsubjects = new ArrayList<String>();
		Map<String, Integer> sortedByCount =sortByValue(recmds);
		
		for (Map.Entry<String, Integer> me : sortedByCount.entrySet()) {
				arrOfsubjects.add(me.getKey());
				System.out.println("==="+me.getKey()+"==="+me.getValue());

		}
		for (Training train : alltrainings) {

			for (String st : arrOfsubjects) {
				if (train.getSubject().contains(st)) {
					recommendedTrainings.add(train);
				}
			}
		}
		return recommendedTrainings;
	}

	@Override
	public List<Training> SimilarTraining(Long userId, Long idTraining) {
		User user = ur.findById(userId).orElse(null);
		Training tr = qr.findById(idTraining).orElse(null);
		List<Certification> crs = user.getCertif();
		List<Training> alltrainings = qr.findAll();
		List<Training> trs = new ArrayList<Training>();
		List<Training> recommendedTrainings = new ArrayList<Training>();
		String s = tr.getSubject();
		for (Certification certif : crs) {
			trs.add(certif.getTraining());
		}
		String[] arrOfStr = s.split(" ", 5);

		alltrainings.removeAll(trs);
		for (Training train : alltrainings) {
			 	
			for (String st : arrOfStr) {

				if (train.getSubject().contains(st)) {
					recommendedTrainings.add(train);
				}
			}
			
		}
		
		return recommendedTrainings;
	}

	@Override
	public void userSatisfaction(Long idUser, Long idTraining , int value) {
		// TODO Auto-generated method stub
		User user = ur.findById(idUser).orElse(null);
		Training tr = qr.findById(idTraining).orElse(null);
		
		if(value == 1){
			tr.getSatisfaction().put(user, true);
			qr.save(tr);
		}else{
			tr.getSatisfaction().put(user, false);
			qr.save(tr);
		}
	}
	
	@Override
	public String trainingOverallSatisfaction(Long idTraining) {
		// TODO Auto-generated method stub
		Training tr = qr.findById(idTraining).orElse(null);
		Map<User,Boolean> satsList=tr.getSatisfaction();

		float alluser=0;
		float satisfied=0;
		for (Map.Entry<User, Boolean> me : satsList.entrySet()) {
			if(me.getValue().equals(true)){
				satisfied+=1;
			}
			alluser+=1;
		}

		log.info("satisfied:"+satisfied);
		log.info("alluser:"+alluser);
 
		float result = (satisfied/alluser)*100;
		tr.setOverAllSatisfaction(Math.round(result));
		qr.save(tr);
		return Math.round(tr.getOverAllSatisfaction())+"% of the users liked this training !!";
	}

	@Override
	public List<Training> ThisWeeksTrainings() {
		// TODO Auto-generated method stub
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate threeDaysAgoDate = LocalDate.now().plusDays(7);
        Date date = Date.from(threeDaysAgoDate.atStartOfDay(defaultZoneId).toInstant());
		List<Training> trs = qr.findAllWithDateAfter(date);

		return trs;
	}

	@Override
	public List<Training> ThisMonthTrainings() {
		// TODO Auto-generated method stub
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate threeDaysAgoDate = LocalDate.now().plusDays(30);
        Date date = Date.from(threeDaysAgoDate.atStartOfDay(defaultZoneId).toInstant());
		List<Training> trs = qr.findAllWithDateAfter(date);

		return trs;
		}

	@Override
	public List<Object[]> MostCertified() {
		List<Object[]> trs=qr.MostCertifiedTrainings();
		for (Object[] objects : trs) {
			System.out.println("****"+objects[0]+"******"+objects[1]);
		}
		 
		return qr.MostCertifiedTrainings();
	}



}
