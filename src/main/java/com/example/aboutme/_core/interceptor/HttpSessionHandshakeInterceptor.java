package com.example.aboutme._core.interceptor;

import com.example.aboutme._core.utils.RedisUtil;
import com.example.aboutme.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

public class HttpSessionHandshakeInterceptor implements HandshakeInterceptor {

    private final RedisUtil redisUtil;

    public HttpSessionHandshakeInterceptor(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            HttpSession session = ((ServletServerHttpRequest) request).getServletRequest().getSession();
            if (session != null) {
                SessionUser sessionUser = redisUtil.getSessionUser();
                if (sessionUser != null) {
                    attributes.put("sessionUser", sessionUser);
                    System.out.println("WebSocket handshake - sessionUser: " + sessionUser.getName());
                } else {
                    System.out.println("Session user is null");
                }
            }
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        // Do nothing
    }
}