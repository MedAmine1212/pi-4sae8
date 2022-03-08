package com.pi.dev.service;

import com.pi.dev.models.ChatBot;
import com.pi.dev.models.Post;
import com.pi.dev.repository.ChatBoRepository;
import com.pi.dev.serviceInterface.IChatBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ChatBotServiceImpl implements IChatBotService {

    @Autowired
    ChatBoRepository chatBotRepository;
    @Override
    public String searchResponse(String text) {
        log.info("aaaaaaaaa: "+text);
        List<ChatBot> responses = new ArrayList<>();
            String[] words = text.toLowerCase().split("\\s");
            for (String word : words) {
                responses.addAll(chatBotRepository.findAllContainingWord("%"+word+"%"));
            }

        if (responses.size() == 0) {
            return "Sorry i couldn't understand that.";
        } else {
            if(responses.size() > 1) {
                for (ChatBot ch : responses) {
                    ch.setScore(Collections.frequency(responses, ch));
                }
                responses.sort(Comparator.comparing(ChatBot::getScore));
                Collections.reverse(responses);
            }
            return  responses.get(0).getResponse();
        }
    }
}
