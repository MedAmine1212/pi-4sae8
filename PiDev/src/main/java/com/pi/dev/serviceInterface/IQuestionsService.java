package com.pi.dev.serviceInterface;

import java.util.List;

import com.pi.dev.models.Questions;



public interface IQuestionsService {
	List<Questions> findAll();

	Questions addQuestions(Questions questions);

	Questions updatePost(Questions questions, Long questionsId);

	boolean deleteQuestions(Long questionsId);



	
}
