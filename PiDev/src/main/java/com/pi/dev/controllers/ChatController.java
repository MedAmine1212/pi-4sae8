package com.pi.dev.controllers;

import com.pi.dev.models.ChatMessage;
import com.pi.dev.models.Message;
import com.pi.dev.models.Room;
import com.pi.dev.models.User;
import com.pi.dev.repository.UserRepository;
import com.pi.dev.serviceInterface.IMessageService;
import com.pi.dev.serviceInterface.IRoomService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@Api(tags = "Chatroom")
@RequestMapping("/chatRoom")
@Slf4j
public class ChatController {

    @Autowired
    IRoomService roomService;


    @Autowired
    UserRepository userRepository;

    @Autowired
    IMessageService messageService;

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/chat.register/{roomId}")
    @SendTo("/topic/public/{roomId}")
    public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        User user = userRepository.findById(chatMessage.getSenderId()).get();
        Room room =roomService.findById(chatMessage.getRoomId());
        user.setActualRoom(room);
        userRepository.save(user);
        for(Message m: messageService.getMessagesByRoom(room.getRoomId())) {
            ChatMessage ch = new ChatMessage();
            ch.setSender(m.getMessageOwner().getUsername());
            ch.setSenderId(m.getMessageOwner().getId());
            ch.setRoomId(room.getRoomId());
            ch.setRequestedBy(chatMessage.getSenderId());
            ch.setContent(m.getMessageText());
            ch.setType(ChatMessage.MessageType.REFRESH);
            this.template.convertAndSend("/topic/public/"+room.getRoomId(), ch);
        }
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

    @MessageMapping("/chat.send/{roomId}")
    @SendTo("/topic/public/{roomId}")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        Message message = new Message();
        message.setMessageOwner(userRepository.findById(chatMessage.getSenderId()).get());
        message.setRoom(roomService.findById(chatMessage.getRoomId()));
        message.setMessageText(chatMessage.getContent());
        messageService.addMessage(message);
        return chatMessage;
    }

    @MessageMapping("/chat.leave/{roomId}")
    @SendTo("/topic/public/{roomId}")
    public ChatMessage leave(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        User user = userRepository.findById(chatMessage.getSenderId()).get();
        if(user.isRoomOwner()) {
            roomService.removeRoom(chatMessage.getRoomId());
            chatMessage.setSender("roomDestroyed");
            headerAccessor.getSessionAttributes().put("username", "roomDestroyed");
        } else {
            roomService.leaveRoom(chatMessage.getSenderId());
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        }
        return chatMessage;
    }

    @PostMapping("/getChatRooms")
    @ResponseBody
    public List<Room> getChatRooms() {
        return roomService.findAll();
    }

    @PostMapping("/getChatRoom/{roomId}")
    @ResponseBody
    public String getChatRoom(@PathVariable Long roomId) {
        return roomService.findById(roomId).getSubject();
    }

    @GetMapping("/createChatRoom/{topic}/{userId}")
    @ResponseBody
    public Room createChatRoom(@PathVariable String topic, @PathVariable Long userId) {
        Room room = new Room();
        room.setSubject(topic);
        return roomService.createRoom(room, userId);
    }

    @GetMapping("/getUserName/{userId}")
    @ResponseBody
    public ChatMessage getUserName(@PathVariable Long userId) {
        ChatMessage chatMessage = new ChatMessage();
        User user =  userRepository.findById(userId).get();
        chatMessage.setSenderId(user.getId());
        chatMessage.setSender(user.getUsername());
        if(user.getActualRoom() != null) {
            chatMessage.setRoomId(user.getActualRoom().getRoomId());
        }
        return chatMessage;
    }

}
