package com.pi.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.dev.models.Questions;
import com.pi.dev.models.Quiz;
import com.pi.dev.models.Training;
import com.pi.dev.repository.QuestionsRepository;
import com.pi.dev.repository.QuizRepository;
import com.pi.dev.repository.TrainingRepository;
import com.pi.dev.serviceInterface.IQuestionsService;
import com.pi.dev.serviceInterface.IQuizService;
import com.pi.dev.serviceInterface.ITrainingService;

@Service
public class QuizServiceImpl implements IQuizService {

	@Autowired
	QuizRepository quir;

	@Autowired
	QuestionsRepository qtsr;

	@Autowired
	TrainingRepository trainrepo;

	@Override
	public List<Quiz> findAll() {
		// TODO Auto-generated method stub
		return quir.findAll();
	}

	@Override
	public Quiz addQuiz(Quiz quiz) {
		List<Questions> questions = (List<Questions>) quiz.getQuestions();
		qtsr.saveAll(questions);
		for (Questions question : questions) {
			question.setQuiz(quiz);

		}
		return quir.save(quiz);

	}

	@Override
	public Quiz updateQuiz(Quiz Quiz, Long quizId) {
		Quiz.setQuizId(quizId);
		List<Questions> questions = (List<Questions>) Quiz.getQuestions();
		qtsr.saveAll(questions);
		for (Questions question : questions) {
			question.setQuiz(Quiz);

		}
		return quir.save(Quiz);
	}

	@Override
	public Quiz findById(Long questionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteQuizById(Long quizId) {
		// TODO Auto-generated method stub
		quir.deleteById(quizId);
	}

	@Override
	public void deleteQuiz(Quiz q) {
		// TODO Auto-generated method stub

	}
    
	@Override
	public Quiz findByTraining(Long trainingId) {
		// TODO Auto-generated method stub*
		Training tr = trainrepo.findById(trainingId).orElse(null);
		Quiz qz = tr.getQuiz();

		return qz;

	}



}
