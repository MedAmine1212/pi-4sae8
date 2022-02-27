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
    /*	int score=0;
		List<Questions> quest = qs.findAll(); 
		Scanner keyboardInput=new Scanner(System.in);

		for (Questions questions : quest) {
			System.err.println("key"+questions.getLabel());
			System.err.println("key"+questions.getAnswers());
				String answer = keyboardInput.nextLine();
				if(answer.equals(questions.getPrompt())) {
					System.err.println("yes baby");
					score++;
				
			}

			
			
		}
		System.err.println("slm"+quest);

		System.err.println("urscore"+score);
*/
     }
}

