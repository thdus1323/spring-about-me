package com.example.aboutme.alarm.sse;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sse")
public class SseController {

    private final SseService sseService;
    private static final Logger logger = LoggerFactory.getLogger(SseController.class);

    @GetMapping(path = "/client/{clientId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribeClient(@PathVariable("clientId") Integer clientId) {
        SseEmitter emitter = sseService.subscribeClient(clientId);
        logger.info("Client subscribed: {}", clientId);
        return emitter;
    }

    @GetMapping(path = "/expert/{expertId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribeExpert(@PathVariable("expertId") Integer expertId) {
        SseEmitter emitter = sseService.subscribeExpert(expertId);
        logger.info("Expert subscribed: {}", expertId);
        return emitter;
    }
}
