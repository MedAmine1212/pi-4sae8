package com.pi.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.dev.models.Questions;
import com.pi.dev.repository.QuestionsRepository;
import com.pi.dev.serviceInterface.IPostService;
import com.pi.dev.serviceInterface.IQuestionsService;

@Service
public class QuestionsServiceImpl implements IQuestionsService{
	
	@Autowired
	QuestionsRepository qr;
	@Override
	public List<Questions> findAll() {
		// TODO Auto-generated method stub
		return qr.findAll();
	}

	@Override
	public Questions addQuestions(Questions questions) {
		// TODO Auto-generated method stub
		return qr.save(questions);
	}

	@Override
	public Questions updatePost(Questions questions, Long questionsId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteQuestions(Long questionsId) {
		// TODO Auto-generated method stub
		return false;
	}

}
