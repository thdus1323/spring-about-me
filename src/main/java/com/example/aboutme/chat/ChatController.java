package com.example.aboutme.chat;

import com.example.aboutme._core.utils.RedisUtil;
import com.example.aboutme.counsel.CounselService;
import com.example.aboutme.user.SessionUser;
import com.example.aboutme.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
@RequiredArgsConstructor
@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;
    private final CounselService counselService;
    private final RedisUtil redisUtil;

    @MessageMapping("/sendMessage")
    public void sendMessage(ChatReqDTO message, SimpMessageHeaderAccessor headerAccessor) {
        // 메시지 발신자 설정
        System.out.println("Received message from client: " + message);

        // 메시지 저장
        chatService.saveChatMessage(message.getId(), message.getContent(), message.getCounselId());

        // 클라이언트로 메시지 전송
        messagingTemplate.convertAndSend("/topic/messages", message);
    }

    @PostMapping("text/complete/{counselId}")
    public String textTherapyComplete(@PathVariable Integer counselId) {
        SessionUser sessionUser = redisUtil.getSessionUser();

        counselService.CounselComplete(sessionUser,counselId);


        return "redirect:/client/myPage";
    }
}