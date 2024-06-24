package com.example.aboutme._core.config;

import com.example.aboutme._core.utils.RedisUtil;
import com.example.aboutme.user.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalModelAttribute {

    private final RedisUtil redisUtil;

    @ModelAttribute("sessionUser")
    public SessionUser addSessionUserToModel() {
        return redisUtil.getSessionUser();
    }
}
