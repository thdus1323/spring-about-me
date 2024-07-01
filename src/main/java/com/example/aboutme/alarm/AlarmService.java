package com.example.aboutme.alarm;

import com.example.aboutme._core.error.exception.Exception404;
import com.example.aboutme.user.SessionUser;
import com.example.aboutme.user.User;
import com.example.aboutme.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AlarmService {
    private final AlarmRepository alarmRepository;
    private final UserRepository userRepository;

    public Integer getUnreadCount(SessionUser sessionUser) {
        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception404("해당 정보를 찾을 수 없습니다"));
        return alarmRepository.countUnreadAlarmsByReceiverId(user.getId());
    }
}
