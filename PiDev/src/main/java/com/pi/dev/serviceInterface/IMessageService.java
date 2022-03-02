package com.pi.dev.serviceInterface;

import com.pi.dev.models.Message;
import com.pi.dev.models.Room;

import java.util.List;

public interface IMessageService {

	List<Message> getMessagesByRoom(Long roomId);

	Message addMessage(Message message);

	boolean deleteMessage(Long messageId);

}
