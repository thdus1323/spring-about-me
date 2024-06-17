package com.example.aboutme._core.interceptor;

import com.example.aboutme._core.error.exception.Exception401;
import com.example.aboutme.user.SessionUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;


public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        SessionUser sessionUser = (SessionUser) session.getAttribute("SessionUser");
        if (sessionUser == null) {
            throw new Exception401("로그인 하셔야 해요");
        }
        return true;
    }
}
