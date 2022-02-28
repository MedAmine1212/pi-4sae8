package com.pi.dev.controllers;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pi.dev.models.CodeGenerator;
import com.pi.dev.models.Quiz;
import com.pi.dev.models.Training;
import com.pi.dev.serviceInterface.ITrainingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

@RestController
@Api(tags = "Manage trainings")
@RequestMapping("/trainings")
public class TrainingRestController {
	@Autowired
	ITrainingService qs;
    //http://localhost:8087/SpringMVC/swagger-ui/index.html
	
	@ApiOperation(value = "add training")
	@PostMapping("/add-training")
	@ResponseBody
	public Training addtraining(@RequestBody Training Training) {
		return qs.addTraining(Training);
	}
	
	
	
	
	@ApiOperation(value = "Get Training list")
	@GetMapping("/getTraining")
	@ResponseBody
	public List<Training> getTraining() {
		return qs.findAll();
	}
	
	@ApiOperation(value = "Get Training list between dates")
	@GetMapping("/getTrainingBetweenDates/{training-day1}/{training-day2}")
	@ResponseBody
	public List<Training> getTrainingsBetweenDates(@PathVariable("training-day1") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate ,
			@PathVariable("training-day2") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) {
		return qs.findbetweendates(startDate, endDate);
	}
	
	@ApiOperation(value = "Get Trainings by subject")
	@GetMapping("/getTrainingBysubject/{subject}")
	@ResponseBody
	public List<Training> getTrainingsBySubject(@PathVariable("subject") String s) {
		return qs.getTrainingBySubject(s);
	}
	
	@ApiOperation(value = "Get recommended trainings")
	@GetMapping("/recommendedTrainings/{user-id}")
	@ResponseBody
	public List<Training> recommendedTrainings(@PathVariable("user-id") Long userId) {
		return qs.recommendedTraining(userId);
	}
	
	@ApiOperation(value = "Update training")
	@PutMapping("/update-training/{training-id}")
	@ResponseBody
	public Training updatetraining(@RequestBody Training Training, @PathVariable("training-id") Long TrainingId) {
		return qs.updateTraining(Training,TrainingId); 	
	}
	
	@ApiOperation(value = "Delete training")
	@DeleteMapping("/delete-training/{training-id}")
	@ResponseBody
	public void deletetraining(@PathVariable("training-id") Long TrainingId) {
		 qs.deleteTrainingById(TrainingId);	
	}
	
	
	@ApiOperation(value = "getTrainingById")
	@GetMapping("/getTrainingById/{training-id}")
	@ResponseBody
	public Training getTrainingById(@PathVariable("training-id") Long TrainingId) {
		 return qs.getTraining(TrainingId);
	}
	
	@PutMapping("/assign-Quiz-Training/{Training-id}")
	@ApiOperation(value = "Assign quiz to training" )
	@ResponseBody
	public void AssignFournisseurToProduct(@RequestBody Quiz qz,@PathVariable("Training-id") Long trainingId){
		qs.assignQuizToTraining(trainingId, qz);
	}
	
	@PostMapping("/ParticipateToTraining/{User-id}/{Training-id}")
	@ApiOperation(value = "ParticipateToTraining" )
	@ResponseBody
	public void ParticipateToTraining(@PathVariable("User-id") Long userId ,@PathVariable("Training-id") Long trainingId){
		qs.ParticipateToTraining(userId, trainingId);
	}
	
	@ApiOperation(value = "cancel Participation")
	@DeleteMapping("/cancel-participation/{User-id}/{Training-id}")
	@ResponseBody
	public void cancelParticipation(@PathVariable("User-id") Long userId ,@PathVariable("Training-id") Long trainingId) {
		 qs.AnnulerParticipation(userId, trainingId);
	}
	//QR Code Generator Test v1
	@ApiOperation(value = "QR Code test")
	@PostMapping(value = "/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> zxingQRCode(@RequestBody String barcode) throws Exception{
        return successResponse(CodeGenerator.generateQRCode(barcode));
    }

    private ResponseEntity<BufferedImage> successResponse(BufferedImage image) {
        return new ResponseEntity<>(image, HttpStatus.OK);
    }
	
	@Bean
    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }
}
