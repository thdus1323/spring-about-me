package com.example.aboutme.alarm.sse;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SseService {
    private final List<SseEmitter> clientEmitters = new CopyOnWriteArrayList<>();
    private final List<SseEmitter> expertEmitters = new CopyOnWriteArrayList<>();

    public void addClientEmitter(SseEmitter emitter) {
        clientEmitters.add(emitter);
        emitter.onCompletion(() -> clientEmitters.remove(emitter));
        emitter.onTimeout(() -> clientEmitters.remove(emitter));
        emitter.onError((e) -> clientEmitters.remove(emitter));
    }

    public void addExpertEmitter(SseEmitter emitter) {
        expertEmitters.add(emitter);
        emitter.onCompletion(() -> expertEmitters.remove(emitter));
        emitter.onTimeout(() -> expertEmitters.remove(emitter));
        emitter.onError((e) -> expertEmitters.remove(emitter));
    }

    @Scheduled(fixedRate = 1000) // 1초마다 이 메서드를 실행한다.
    public void sendEvents() {
        sendEventsToEmitters(clientEmitters, "Hello, Client!");
        sendEventsToEmitters(expertEmitters, "Hello, Expert!");
    }

    private void sendEventsToEmitters(List<SseEmitter> emitters, String message) {
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(message);
            } catch (IOException e) {
                emitter.complete();
                emitters.remove(emitter);
            }
        }
    }
}
