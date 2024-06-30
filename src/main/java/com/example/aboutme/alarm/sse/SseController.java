package com.example.aboutme.alarm.sse;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sse")
public class SseController {

    @Autowired
    private SseService sseService;

    @GetMapping(path = "/client/{clientId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribeClient(@PathVariable("clientId") Integer clientId) {
        return sseService.subscribeClient(clientId);
    }

    @GetMapping(path = "/expert/{expertId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribeExpert(@PathVariable("expertId") Integer expertId) {
        return sseService.subscribeExpert(expertId);
    }
}