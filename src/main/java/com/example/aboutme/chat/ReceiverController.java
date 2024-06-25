package com.example.aboutme.chat;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiverController {

    private final SimpMessagingTemplate messagingTemplate;

    public ReceiverController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping("/receive-data")
    public String receiveData(@RequestBody ChatMessage message) {
        System.out.println("Received data: " + message.getContent());
        messagingTemplate.convertAndSend("/topic/messages", message);
        return "Data received successfully";
    }
}