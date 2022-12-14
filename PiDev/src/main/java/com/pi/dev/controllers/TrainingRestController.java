package com.pi.dev.controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.EncodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.pi.dev.models.CodeGenerator;
import com.pi.dev.models.Quiz;
import com.pi.dev.models.Training;
import com.pi.dev.models.User;
import com.pi.dev.repository.TrainingRepository;
import com.pi.dev.repository.UserRepository;
import com.pi.dev.serviceInterface.ITrainingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.var;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StreamUtils;

@RestController
@Api(tags = "Manage trainings")
@RequestMapping("/trainings")
public class TrainingRestController {
	@Autowired
	ITrainingService qs;
	
	@Autowired
	UserRepository userrepository;
	@Autowired
	TrainingRepository tr;
	// http://localhost:8087/SpringMVC/swagger-ui/index.html

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
	public List<Training> getTrainingsBetweenDates(
			@PathVariable("training-day1") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@PathVariable("training-day2") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
		return qs.findbetweendates(startDate, endDate);
	}

	@ApiOperation(value = "Get Trainings by subject")
	@GetMapping("/getTrainingBysubject/{user-id}/{subject}")
	@ResponseBody
	public List<Training> getTrainingsBySubject(@PathVariable("user-id") Long userId,
			@PathVariable("subject") String s) {
		return qs.getTrainingBySubject(userId, s);
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
		return qs.updateTraining(Training, TrainingId);
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
	@ApiOperation(value = "Assign quiz to training")
	@ResponseBody
	public void AssignFournisseurToProduct(@RequestBody Quiz qz, @PathVariable("Training-id") Long trainingId) {
		qs.assignQuizToTraining(trainingId, qz);
	}

	@PostMapping("/ParticipateToTraining/{User-id}/{Training-id}")
	@ApiOperation(value = "ParticipateToTraining")
	@ResponseBody
	public void ParticipateToTraining(@PathVariable("User-id") Long userId,
			@PathVariable("Training-id") Long trainingId) {
		qs.ParticipateToTraining(userId, trainingId);
	}

	@ApiOperation(value = "cancel Participation")
	@DeleteMapping("/cancel-participation/{User-id}/{Training-id}")
	@ResponseBody
	public void cancelParticipation(@PathVariable("User-id") Long userId,
			@PathVariable("Training-id") Long trainingId) {
		qs.AnnulerParticipation(userId, trainingId);
	}

	// QR Code Generator Test v1
	@ApiOperation(value = "QR Code test")
	@PostMapping(value = "/qrcode/{user-id}/{training-id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> zxingQRCode(@PathVariable("user-id") Long idUser,
			@PathVariable("training-id") Long idTraining) throws WriterException, IOException, NotFoundException {
		User user = userrepository.findById(idUser).orElse(null);
		Training train = tr.findById(idTraining).orElse(null);

		// path where we want to get QR Code
		String path = "C:\\Users\\Mr.Khlifi\\OneDrive\\Bureau\\backup\\pi-4sae8\\PiDev\\src\\main\\resources\\images\\myQrCode.jpg";

		// Encoding charset to be used
		String charset = "UTF-8";
		Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
		// generates QR code with Low level(L) error correction capability
		hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		// invoking the user-defined method that creates the QR code
		String str = " Congratz !! This is your certification " + user.getFirstName() + " " + user.getLastName()
				+ " On : " + train.getSubject() + " .";

		CodeGenerator.generateQRcode(str, path, charset, hashMap, 200, 200);// increase or decrease height and width
																			// accodingly
		// prints if the QR code is generated
		ClassPathResource imageFile = new ClassPathResource("images/myQrCode.jpg");
		byte[] imageBytes = StreamUtils.copyToByteArray(imageFile.getInputStream());
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	}

	@ApiOperation(value = "Get simlar trainings")
	@GetMapping("/simlartraining/{user-id}/{training-id}")
	@ResponseBody
	public List<Training> similarTrainings(@PathVariable("user-id") Long userId,
			@PathVariable("training-id") Long trainingId) {
		return qs.SimilarTraining(userId, trainingId);
	}
	@ApiOperation(value = "Get recommendedBySearch")
	@GetMapping("/recommendedBySearch/{user-id}")
	@ResponseBody
	public List<Training> recommendedBySearch(@PathVariable("user-id") Long userId) {
		return qs.recommendedTrainingBySearchs(userId);
	}
	
	@PutMapping("/setSatisfaction/{User-id}/{Training-id}")
	@ApiOperation(value = "userSatisfaction")
	@ResponseBody
	public void userSatisfaction(@PathVariable("User-id") Long userId, @PathVariable("Training-id") Long trainingId,
			@RequestParam int value) {
		qs.userSatisfaction(userId, trainingId, value);
	}

	@ApiOperation(value = "Get users Satisfaction of a training")
	@GetMapping("/overallSatisfaction/{training-id}")
	@ResponseBody
	public String trainingSatisfaction(@PathVariable("training-id") Long trainingId) {
		return qs.trainingOverallSatisfaction(trainingId);
	}

	@ApiOperation(value = "This Week's trainings")
	@GetMapping("/thisWekk")
	@ResponseBody
	public List<Training> thisWeekTrainings() {
		return qs.ThisWeeksTrainings();
	}

	@ApiOperation(value = "This Month's trainings")
	@GetMapping("/thisMonth")
	@ResponseBody
	public List<Training> thisMonthTrainings() {
		return qs.ThisMonthTrainings();
	}

	@ApiOperation(value = "MostCertifiedTrainings")
	@GetMapping("/most")
	@ResponseBody
	public List<Object[]> certifeTrainings() {
		return qs.MostCertified();
	}
}
