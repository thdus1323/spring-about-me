package com.example.aboutme.alarm.sse;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SseService {
    private static final Logger logger = LoggerFactory.getLogger(SseService.class);
    private final Map<Integer, SseEmitter> clientEmitters = new ConcurrentHashMap<>();
    private final Map<Integer, SseEmitter> expertEmitters = new ConcurrentHashMap<>();

    public SseEmitter subscribeClient(Integer clientId) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        clientEmitters.put(clientId, emitter);
        emitter.onCompletion(() -> clientEmitters.remove(clientId));
        emitter.onTimeout(() -> clientEmitters.remove(clientId));
        logger.info("Client emitter created for client: {}", clientId);
        return emitter;
    }

    public SseEmitter subscribeExpert(Integer expertId) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        expertEmitters.put(expertId, emitter);
        emitter.onCompletion(() -> expertEmitters.remove(expertId));
        emitter.onTimeout(() -> expertEmitters.remove(expertId));
        logger.info("Expert emitter created for expert: {}", expertId);
        return emitter;
    }

    public void notifyClient(Integer clientId, String message) {
        SseEmitter emitter = clientEmitters.get(clientId);
        if (emitter != null) {
            try {
                String formattedMessage = String.format("event: message\ndata: %s\n\n", message);
                logger.info("Sending message to client {}: {}", clientId, formattedMessage);
                emitter.send(formattedMessage);
            } catch (IOException e) {
                logger.error("Error sending message to client: {}", clientId, e);
                emitter.completeWithError(e);
                clientEmitters.remove(clientId);
            }
        }
    }

    public void notifyExpert(Integer expertId, String message) {
        SseEmitter emitter = expertEmitters.get(expertId);
        if (emitter != null) {
            try {
                String formattedMessage = String.format("event: message\ndata: %s\n\n", message);
                logger.info("Sending message to expert {}: {}", expertId, formattedMessage);
                emitter.send(formattedMessage);
            } catch (IOException e) {
                logger.error("Error sending message to expert: {}", expertId, e);
                emitter.completeWithError(e);
                expertEmitters.remove(expertId);
            }
        }
    }
}
