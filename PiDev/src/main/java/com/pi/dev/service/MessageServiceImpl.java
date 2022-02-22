package com.pi.dev.service;

import com.pi.dev.models.*;
import com.pi.dev.repository.*;
import com.pi.dev.serviceInterface.ICommentService;
import com.pi.dev.serviceInterface.IMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class MessageServiceImpl implements IMessageService {

	@Autowired
	MessageRepository messageRepository;


	@Autowired
	RoomRepository roomRepository;

	@Override
	public List<Message> getMessagesByRoom(Long roomId) {
		return messageRepository.findAllByRoom(roomRepository.getById(roomId));
	}

	@Override
	public Message addMessage(Message message) {
		if (BadWordFilter.badWordsFound(message.getMessageText()).size() != 0){
			return null;
		} else {
			return messageRepository.save(message);
		}
	}

	@Override
	public boolean deleteMessage(Long messageId) {
		try {
			messageRepository.deleteById(messageId);
			return true;
		} catch (Exception e) {
			return  false;
		}	}
}