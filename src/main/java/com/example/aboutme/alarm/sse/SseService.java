package com.example.aboutme.alarm.sse;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SseService {
    private final Map<Integer, SseEmitter> clientEmitters = new ConcurrentHashMap<>();
    private final Map<Integer, SseEmitter> expertEmitters = new ConcurrentHashMap<>();

    public SseEmitter subscribeClient(Integer clientId) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        clientEmitters.put(clientId, emitter);
        emitter.onCompletion(() -> clientEmitters.remove(clientId));
        emitter.onTimeout(() -> clientEmitters.remove(clientId));
        return emitter;
    }

    public SseEmitter subscribeExpert(Integer expertId) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        expertEmitters.put(expertId, emitter);
        emitter.onCompletion(() -> expertEmitters.remove(expertId));
        emitter.onTimeout(() -> expertEmitters.remove(expertId));
        return emitter;
    }

    public void notifyClient(Integer clientId, String message) {
        SseEmitter emitter = clientEmitters.get(clientId);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event().name("clientEvent").data(message));
            } catch (IOException e) {
                emitter.completeWithError(e);
                clientEmitters.remove(clientId);
            }
        }
    }

    public void notifyExpert(Integer expertId, String message) {
        SseEmitter emitter = expertEmitters.get(expertId);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event().name("expertEvent").data(message));
            } catch (IOException e) {
                emitter.completeWithError(e);
                expertEmitters.remove(expertId);
            }
        }
    }
}
