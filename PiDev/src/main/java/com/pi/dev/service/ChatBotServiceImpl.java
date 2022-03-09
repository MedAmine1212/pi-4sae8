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
                if(word.length() >3) {
                    responses.addAll(chatBotRepository.findAllContainingWord("%" + word + "%"));
                }
            }

        if (responses.size() == 0) {
            if(text.toLowerCase().contains("Hello".toLowerCase() ) || text.toLowerCase().contains("Morning".toLowerCase())|| text.toLowerCase().contains("Help".toLowerCase())) {
                return "Good Day to you, how can I be of service ?";
            } else if(text.toLowerCase().contains("Thank".toLowerCase())|| text.toLowerCase().contains("okay".toLowerCase())|| text.toLowerCase().contains("done".toLowerCase())|| text.toLowerCase().contains("bye".toLowerCase())){
                return "Am glad I can be of service, have a nice day !";
            } else {
                return "Sorry i couldn't understand that.";
            }
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
