package com.example.aboutme.counsel;

import com.example.aboutme._core.utils.RedisUtil;
import com.example.aboutme.counsel.CounselResponseDTO.CounselDTO.CounselDTORecord;
import com.example.aboutme.user.SessionUser;
import com.example.aboutme.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@RequiredArgsConstructor
@Controller
public class CounselController {
    private final CounselService counselService;
    private final RedisUtil redisUtil;
    private final RedisTemplate<String, Object> redisTemp;


    //상담일정
    @GetMapping("/schedule")
    public String schedule(Model model) {
        SessionUser sessionUser = redisUtil.getSessionUser();
        log.info("로그인한 유저 {} ", sessionUser);
        CounselDTORecord counselDTORecord = counselService.findCounsel(sessionUser);
        model.addAttribute("counselList",counselDTORecord);

        return "expert/schedule";
    }
}
