package com.example.aboutme.chat;

import com.example.aboutme._core.utils.RedisUtil;
import com.example.aboutme.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReceiverController {

    private final SimpMessagingTemplate messagingTemplate;
    private final RedisUtil redisUtil;

    public ReceiverController(SimpMessagingTemplate messagingTemplate, RedisUtil redisUtil) {
        this.messagingTemplate = messagingTemplate;
        this.redisUtil = redisUtil;
    }

    @PostMapping("/receive-data")
    public String receiveData(@RequestBody ChatReqDTO message) {
        System.out.println("Received data: " + message.getContent());
        messagingTemplate.convertAndSend("/topic/messages", message);
        return "Data received successfully";
    }

    @GetMapping("/api/sessionUser")
    @ResponseBody
    public ResponseEntity<SessionUser> getSessionUser(HttpSession session) {
        SessionUser sessionUser = redisUtil.getSessionUser();
        if (sessionUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // 세션에 사용자가 없는 경우
        }
        return ResponseEntity.ok(sessionUser);
    }
}