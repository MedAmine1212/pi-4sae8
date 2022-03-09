package com.pi.dev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pi.dev.models.Interview;
import com.pi.dev.serviceInterface.IInterviewService;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Manage Interviews")
@RequestMapping("/interview")
public class InterviewController {

	//http://localhost:8085/SpringMVC/swagger-ui/index.html
	
		@Autowired
		IInterviewService interviewService;
		
		
		@ApiOperation(value = "Get Interviews list")
		@GetMapping("/getAllInterview")
		@ResponseBody
		public List<Interview> getAllInterview() {
			return interviewService.findAllInterview();
		}
		
		
		@ApiOperation(value = "Add Interview")
		@PostMapping("/addInterview")
		@ResponseBody
		public Interview addInterview(@RequestBody Interview f) {
			return interviewService.addInterview(f);
		}
		
		@ApiOperation(value = "Update a Interview")
		@PostMapping("/updateInterview")
		@ResponseBody
		public Interview updateInterview(@RequestParam Long idInterview, @RequestBody Interview interview) {
			return interviewService.updateInterview(idInterview, interview);
		}
		
		@ApiOperation(value = "Delete a Interview")
		@DeleteMapping("/deleteInterview")
		@ResponseBody
		public boolean deleteInterview(@RequestParam Long idInterview) {
			try {
				interviewService.deleteInterview(idInterview);
				return true;
			} catch (Exception e) {
				return  false;
			}
			
		}
}
