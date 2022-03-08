package com.pi.dev.serviceInterface;

import java.util.List;

import com.pi.dev.models.Trainer;

public interface ITrainerService {
    List<Trainer> findAll();

	Trainer addTrainer(Trainer trainer);

	Trainer updateTrainer(Trainer trainer, Long trainerId);

	void deleteTrainerById(Long trainerId);

    public void assignTrainerToTraining(Long trainerId,Long trainingId);
    public void StayHated(Long userId);
    void removeHated();

}
