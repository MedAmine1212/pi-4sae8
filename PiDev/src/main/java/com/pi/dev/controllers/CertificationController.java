package com.pi.dev.controllers;

import com.pi.dev.models.Quiz;
import com.pi.dev.serviceInterface.ICertificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@Api(tags = "Manage certification")
@RequestMapping("/certifications")
public class CertificationController {
	@Autowired
	ICertificationService cs;
 
    @ApiOperation(value = "take quiz")
	@PostMapping("/take-quiz/{user-id}/{quiz-id}/{answers}")
	@ResponseBody
	public String takeQuiz(@PathVariable("user-id")Long idUser, @PathVariable("quiz-id") Long idQuiz,@PathVariable("answers") String s) {
		return cs.takeQuiz(idUser, idQuiz, s);
	}
}
