package com.pi.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableScheduling
@SpringBootApplication
@EnableAspectJAutoProxy
public class PiDevApplication {

	public static void main(String[] args) {
		SpringApplication.run(PiDevApplication.class, args);
	}

}
