package com.pi.dev;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.pi.dev.models.Questions;
import com.pi.dev.serviceInterface.IQuestionsService;

@Component
public class MyRunner implements CommandLineRunner {
	@Autowired
	private IQuestionsService qs;
	
    @Override
    public void run(String... args) throws Exception {
    	int score=0;
		Scanner keyboardInput=new Scanner(System.in);
		List<Questions> quest = qs.findAll(); 
		for (Questions questions : quest) {
			String answer = keyboardInput.nextLine();
			System.err.println("key"+answer);

			if(questions.getAnswers().contains(answer)) {
				score++;
			}
		}
		System.err.println("slm"+quest);

		System.err.println("urscore"+score);

     }
}
