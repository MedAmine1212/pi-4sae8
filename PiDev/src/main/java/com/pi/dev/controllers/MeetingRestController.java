package com.pi.dev.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import com.pi.dev.models.Meeting;
import com.pi.dev.service.MeetingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "Manage meetings")
@RequestMapping("/meeting")
public class MeetingRestController {
    @Autowired
    MeetingService ms;
    @PostMapping("/add-meeting")
	@ResponseBody
	public Meeting addMeeting(@RequestBody Meeting meeting) {
		return ms.addMeeting(meeting);
	}
    @GetMapping("/getMeetings")
	@ResponseBody
	public List<Meeting> getQuestions() {
		return ms.listMeetings();
	}
	@GetMapping("/getMeetings/{idContributor}")
	@ResponseBody
	public List<Meeting> listMeetingDispo(@PathVariable Long idContributor) {
		return ms.listMeetingsByDisponibilty(idContributor);
	}
	
	
}
