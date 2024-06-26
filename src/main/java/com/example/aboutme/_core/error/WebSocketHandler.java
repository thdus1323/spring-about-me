package com.example.aboutme._core.error;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private static final Map<String, WebSocketSession> clients = Collections.synchronizedMap(new HashMap<>());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String clientId = getClientId(session);
        clients.put(clientId, session);
        System.out.println("Client connected: " + clientId);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String clientId = getClientId(session);
        String payload = message.getPayload();
        System.out.println("Message from " + clientId + ": " + payload);

        // For simplicity, broadcasting message to all connected clients
        synchronized (clients) {
            for (WebSocketSession client : clients.values()) {
                if (client.isOpen()) {
                    client.sendMessage(new TextMessage(payload));
                }
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String clientId = getClientId(session);
        clients.remove(clientId);
        System.out.println("Client disconnected: " + clientId);
    }

    private String getClientId(WebSocketSession session) {
        return session.getId();
    }

    private HttpSession getHttpSession(WebSocketSession session) {
        return (HttpSession) session.getAttributes().get("HTTP_SESSION");
    }
}