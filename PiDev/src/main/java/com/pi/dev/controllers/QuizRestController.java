package com.pi.dev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pi.dev.models.Quiz;
import com.pi.dev.models.Training;
import com.pi.dev.serviceInterface.IQuizService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Manage quiz")
@RequestMapping("/quiz")
public class QuizRestController {
	@Autowired
	IQuizService qs;
	
	@ApiOperation(value = "add quiz")
	@PostMapping("/add-quiz")
	@ResponseBody
	public Quiz addQuiz(@RequestBody Quiz quiz) {
		return qs.addQuiz(quiz);
	}
	
	@ApiOperation(value = "Update quiz")
	@PutMapping("/update-quiz/{quiz-id}")
	@ResponseBody
	public Quiz updateQuiz(@RequestBody Quiz quiz, @PathVariable("quiz-id") Long TrainingId) {
		return qs.updateQuiz(quiz,TrainingId); 	
	}
	
	@ApiOperation(value = "Delete quiz")
	@DeleteMapping("/delete-quiz/{quiz-id}")
	@ResponseBody
	public void deletequiz(@PathVariable("quiz-id") Long quizId) {
		 qs.deleteQuizById(quizId);	
	}
	
	@ApiOperation(value = "Get quiz list")
	@GetMapping("/getQuizs")
	@ResponseBody
	public List<Quiz> getquizs() {
		return qs.findAll();
	}
}
