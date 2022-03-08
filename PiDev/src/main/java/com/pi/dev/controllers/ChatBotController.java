package com.pi.dev.controllers;

import com.pi.dev.models.Comment;
import com.pi.dev.serviceInterface.IChatBotService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/chatbot")
public class ChatBotController {

    @Autowired
    IChatBotService chatBotService;
    @PostMapping("/search")
    @ResponseBody
    String getResponse(@RequestBody String text) {
        text = text.substring(text.lastIndexOf("=")+1);
        text = text.replaceAll("\\+", " ");
        log.info(text);
        return chatBotService.searchResponse(text);
    }
}
