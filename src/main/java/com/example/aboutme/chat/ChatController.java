package com.example.aboutme.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/sendMessage")
    public void sendMessage(@Payload ChatMessage message) {
        System.out.println("Received message: " + message.getContent());
        // 메시지를 발신자와 수신자 모두에게 다시 전송
        messagingTemplate.convertAndSendToUser(message.getRecipient(), "/queue/messages", message);
        messagingTemplate.convertAndSendToUser(message.getSender(), "/queue/messages", message);
    }
}