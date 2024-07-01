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

    public Integer incrementUnreadCount(SessionUser sessionUser) {
        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception404("해당 정보를 찾을 수 없습니다"));

        // 여기서는 단순히 현재 카운트를 증가시켜 반환하는 것처럼 처리합니다.
        // 실제로는 알람을 생성하는 로직 등이 필요할 수 있습니다.
        Integer unreadCount = alarmRepository.countUnreadAlarmsByReceiverId(user.getId());
        return unreadCount + 1;
    }
}
