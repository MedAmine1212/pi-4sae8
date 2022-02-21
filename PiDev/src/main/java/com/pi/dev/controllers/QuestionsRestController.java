package com.pi.dev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pi.dev.models.Questions;
import com.pi.dev.serviceInterface.IQuestionsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Manage questions")
@RequestMapping("/questions")
public class QuestionsRestController {

	
	@Autowired
	IQuestionsService qs;
    //http://localhost:8087/SpringMVC/swagger-ui/index.html
	
	@ApiOperation(value = "add question")
	@PostMapping("/add-question")
	@ResponseBody
	public Questions addQuestion(Questions questions) {
		return qs.addQuestions(questions);
	}
	
	@ApiOperation(value = "Get questions list")
	@GetMapping("/getQuestions")
	@ResponseBody
	public List<Questions> getQuestions() {
		return qs.findAll();
	}

}
