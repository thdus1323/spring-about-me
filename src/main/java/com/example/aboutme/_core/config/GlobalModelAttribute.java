package com.example.aboutme._core.config;

import com.example.aboutme._core.utils.RedisUtil;
import com.example.aboutme.user.SessionUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class GlobalModelAttribute {

    private final RedisUtil redisUtil;

    @ModelAttribute("sessionUser")
    public SessionUser addSessionUserToModel() {
        SessionUser sessionUser =redisUtil.getSessionUser();
        log.info("전역 세선 유저 {} 👍👍👍👍👍👍👍",redisUtil.getSessionUser());
        if (sessionUser != null) {
            sessionUser.determineRoles(); // 역할 설정
            return sessionUser;
        }
        return null;
    }
}
