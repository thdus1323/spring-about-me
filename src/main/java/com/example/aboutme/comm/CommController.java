package com.example.aboutme.comm;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class CommController {
    private final CommService commService;

    @GetMapping("/comm/write")
    public String communityWrite() {
        return "comm/comm-write";
    }

    //TODO: 커뮤니티 페이지
    //커뮤니티 - 메인
    @GetMapping("/comm")
    public String community() {
        return "comm/comm-main";
    }

    @GetMapping("comm/detail")
    public String communityDetail() {
        return "comm/comm-detail";
    }
}
