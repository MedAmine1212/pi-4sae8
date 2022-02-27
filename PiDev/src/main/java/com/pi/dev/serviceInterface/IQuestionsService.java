package com.pi.dev.serviceInterface;

import java.util.List;

import com.pi.dev.models.Questions;



public interface IQuestionsService {
	List<Questions> findAll();

	Questions addQuestions(Questions questions);

	Questions updateQuestion(Questions questions,Long questionId);

	Questions findById(Long questionId);
	
	public void deleteQuestionsById(Long questionsId);
	
	public void deleteQuestion(Questions q);


	
}
