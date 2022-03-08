package com.pi.dev;

import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.pi.dev.models.Questions;
import com.pi.dev.serviceInterface.IQuestionsService;


@SpringBootTest
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableScheduling
@EnableConfigurationProperties

class PiDevApplicationTests {
	
	@Test
	void contextLoads() {
		
		
	}

}
