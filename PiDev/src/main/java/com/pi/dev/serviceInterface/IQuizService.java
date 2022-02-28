package com.pi.dev.serviceInterface;

import java.util.List;

import com.pi.dev.models.Quiz;

public interface IQuizService {
	List<Quiz> findAll();

	Quiz addQuiz(Quiz quiz);

	Quiz updateQuiz(Quiz Quiz,Long quizId);

	Quiz findById(Long questionId);
	
	Quiz findByTraining(Long trainingId);

	public void deleteQuizById(Long quizId);
	
	public void deleteQuiz(Quiz q);

}
