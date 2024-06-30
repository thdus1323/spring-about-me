package com.example.aboutme.alarm.sse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class SseController {
    @Autowired
    private SseService sseService;

    @GetMapping(path = "/clientEmitter", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribeClient() {
        SseEmitter clientEmitter = new SseEmitter(Long.MAX_VALUE); // 무한 타임아웃을 가진 에미터 객체가 생성됨
        sseService.addClientEmitter(clientEmitter);
        return clientEmitter;
    }

    @GetMapping(path = "/expertEmitter", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribeExpert() {
        SseEmitter expertEmitter = new SseEmitter(Long.MAX_VALUE); // 무한 타임아웃을 가진 에미터 객체가 생성됨
        sseService.addExpertEmitter(expertEmitter);
        return expertEmitter;
    }
}
