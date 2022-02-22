package com.pi.dev.controllers;

import com.pi.dev.models.Comment;
import com.pi.dev.models.CommentReact;
import com.pi.dev.models.Message;
import com.pi.dev.models.Room;
import com.pi.dev.serviceInterface.ICommentService;
import com.pi.dev.serviceInterface.IMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Manage Messages")
@RequestMapping("/message")
public class MessageRestController {

	@Autowired
	IMessageService messageService;

    //http://localhost:8085/swagger-ui/index.html

	@ApiOperation(value = "Get messages by room")
	@GetMapping("/getMessagesByRoom/{roomId}")
	@ResponseBody
	List<Message> getMessagesByRoom(@PathVariable Long roomId){
		return messageService.getMessagesByRoom(roomId);
	}
	@ApiOperation(value = "Add Message")
	@PostMapping("/addMessage")
	@ResponseBody
	Message addMessage(Message message) {
		return messageService.addMessage(message);
	}

	@ApiOperation(value = "Delete Message")
	@DeleteMapping("/deleteMessage/{messageId}")
	@ResponseBody
	boolean deleteMessage(@PathVariable Long messageId){
		return messageService.deleteMessage(messageId);
	}
}
