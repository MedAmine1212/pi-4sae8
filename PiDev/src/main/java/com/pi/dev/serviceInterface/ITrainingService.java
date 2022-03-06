package com.pi.dev.serviceInterface;

import java.util.Date;
import java.util.List;

import com.pi.dev.models.Quiz;
import com.pi.dev.models.Training;

public interface ITrainingService {
	List<Training> findAll();
	List<Training> findbetweendates(Date startDate,Date endDate);

	Training addTraining(Training training);

	Training updateTraining(Training Training,Long trainingId);
	public void AnnulerParticipation(Long userId, Long trainingId);
	Training getTraining(Long TrainingId);
	public void ParticipateToTraining(Long userId, Long trainingId);
	public void deleteTrainingById(Long trainingId);
	void assignQuizToTraining(Long trainingId, Quiz qz);
	List<Training> getTrainingBySubject(Long userId,String s);

	List<Training> SimilarTraining(Long userId , Long idTraining);
    

	public void deleteTraining(Training q);
	
	public List<Training>recommendedTraining(Long idUser);

	public List<Training>recommendedTrainingBySearchs(Long idUser);


}
