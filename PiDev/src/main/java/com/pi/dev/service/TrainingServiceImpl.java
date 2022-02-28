package com.pi.dev.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.dev.models.Certification;
import com.pi.dev.models.Questions;
import com.pi.dev.models.Quiz;
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
		User user = ur.findById(userId).orElse(null);
		Certification certif = new Certification();

		certif.setTraining(tr);
		certif.setUser(user);
		cr.save(certif);
		tr.setMaxNbrParticipants(tr.getMaxNbrParticipants() + 1);
		qr.save(tr);
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
		// TODO Auto-generated method stub
		return recommendedTrainings;
	}

}
