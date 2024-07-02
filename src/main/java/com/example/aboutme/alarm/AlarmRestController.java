package com.example.aboutme.alarm;

import com.example.aboutme._core.utils.RedisUtil;
import com.example.aboutme.user.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AlarmRestController {
    private final AlarmService alarmService;
    private final RedisUtil redisUtil;

    @GetMapping("/sse/alarm-count")
    public Integer alarmCount(){
        SessionUser sessionUser = redisUtil.getSessionUser();
        return alarmService.getUnreadCount(sessionUser);
    }
}
