package com.pi.dev.controllers;

import java.util.List;

import com.pi.dev.models.Trainer;
import com.pi.dev.serviceInterface.ITrainerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Manage trainer")
@RequestMapping("/trainings")
public class TrainerRestController {
    @Autowired
	ITrainerService qs;

    @ApiOperation(value = "add trainer")
	@PostMapping("/add-trainer")
	@ResponseBody
	public Trainer addtrainer(@RequestBody Trainer trainer) {
		return qs.addTrainer(trainer);
	}

    @ApiOperation(value = "Get trainers list")
	@GetMapping("/getTrainers")
	@ResponseBody
	public List<Trainer> getTrainers() {
		return qs.findAll();
	}

    @ApiOperation(value = "Assign trainer to traning")
	@PostMapping("/assign-trainer/{trainer-id}/{training-id}")
	@ResponseBody
	public void assigntrainer(@PathVariable("trainer-id") Long idTrainer ,@PathVariable("training-id") Long idTraining ) {
		qs.assignTrainerToTraining(idTrainer, idTraining);
	}

    @ApiOperation(value = "removepoints")
	@PostMapping("/removepoints/{user-id}")
	@ResponseBody
	public void removepoints(@PathVariable("user-id") Long idUser ) {
		qs.StayHated(idUser);
	}
}
