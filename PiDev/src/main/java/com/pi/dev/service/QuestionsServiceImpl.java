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
	public Questions updateQuestion(Questions questions,Long questionId) {
		questions.setQuestionsId(questionId);
		return qr.save(questions);
	}



	@Override
	public void deleteQuestion(Questions q) {
		// TODO Auto-generated method stub
		qr.delete(q);
	}

	@Override
	public void deleteQuestionsById(Long questionsId) {
		// TODO Auto-generated method stub
		qr.deleteById(questionsId);
	}

	@Override
	public Questions findById(Long questionId) {
		// TODO Auto-generated method stub
		return qr.findById(questionId).orElse(null);
	}

}
