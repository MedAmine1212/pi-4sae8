package com.pi.dev.service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import com.pi.dev.models.ReputationLevel;
import com.pi.dev.models.Trainer;
import com.pi.dev.models.Training;
import com.pi.dev.models.User;
import com.pi.dev.repository.TrainerRepository;
import com.pi.dev.repository.TrainingRepository;
import com.pi.dev.repository.UserRepository;
import com.pi.dev.serviceInterface.ICertificationService;
import com.pi.dev.serviceInterface.ITrainerService;
import com.pi.dev.serviceInterface.ITrainingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TrainerServiceImpl implements ITrainerService {

    @Autowired
    TrainerRepository trainerRepository;
    @Autowired
    TrainingRepository qr;
    @Autowired
	UserRepository ur;
    
    @PersistenceContext
	private EntityManager entityManager;
    
    @Autowired
	ICertificationService certifservice;

    @Override
    public List<Trainer> findAll() {
        // TODO Auto-generated method stub
        return trainerRepository.findAll();
    }

    @Override
    public Trainer addTrainer(Trainer trainer) {
        // TODO Auto-generated method stub
        return trainerRepository.save(trainer);
    }

    @Override
    public Trainer updateTrainer(Trainer trainer, Long trainerId) {
        // TODO Auto-generated method stub
        trainer.setTrainerId(trainerId);
        return trainerRepository.save(trainer);
    }

    @Override
    public void deleteTrainerById(Long trainerId) {
        // TODO Auto-generated method stub
        trainerRepository.deleteById(trainerId);

    }

    @Override
    @Transactional
    public void assignTrainerToTraining(Long trainerId, Long trainingId) {
        // TODO Auto-generated method stub
        Training tr = qr.findById(trainingId).orElse(null);
        Trainer trainer = trainerRepository.findById(trainerId).orElse(null);
        List<Training> trainings = trainer.getTrainings();
        Date d1 = tr.getStartDate();
        Date d2 = tr.getEndDate();
        LocalDate dd=LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(d2));
        System.out.println("the date is :"+ dd);
        LocalDate dk = dd.plusMonths(-1);
        ZoneId defaultZoneId = ZoneId.systemDefault();

        System.out.println("the date is after update :"+ dk);
        LocalDate threeDaysAgoDate = LocalDate.now().minusDays(7);
        Date date = Date.from(threeDaysAgoDate.atStartOfDay(defaultZoneId).toInstant());

        List<Training> trs = qr.findAllWithDateAfter(date);
       for (Training training : trs) {
        System.out.println("the training is:"+ training.getSubject());

       }
        int x = 0;
        for (Training training : trainings) {
            if (d1.after(training.getStartDate()) && d1.before(training.getEndDate())) {
                x+=1;
            }
        }

        System.out.println("this is:"+x);
        if(x<2){
            tr.setTrainer(trainer);
            qr.save(tr); 
        }
        else{
            System.out.println("This trainer already assigned to two trainings , find another one !! ");
        }
    
    }

    @Override
    public void StayHated(Long userId) {
        // TODO Auto-generated method stub
        User user = ur.findById(userId).orElse(null);
        user.setReputationPoints(user.getReputationPoints() - 200);
        certifservice.changeReputation(userId);
        ur.save(user);
           
    }

    @Override
    @Transactional
	@Scheduled(cron="* 00 3 * * 5")
    public void removeHated() {
        List<User> users = ur.findAll();
        for (User user : users) {
            if(user.getReputationLevel().equals(ReputationLevel.Hated)){
                user.setReputationLevel(ReputationLevel.Neutral);
                user.setReputationPoints(0);
                ur.save(user);
            }
        }
    }

}
