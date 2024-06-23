package com.example.aboutme.reply;

import com.example.aboutme.user.SessionUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.http.HttpResponse;

@RequiredArgsConstructor
@Controller
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping("/reply/complete")
    public void complete(@RequestBody ReplyResponse.ReplyDataDTO newReply, HttpSession session) {

        // 세션에서 사용자 정보를 가져옴 = userId
        SessionUser sessionUser = (SessionUser) session.getAttribute("SessionUser");


        // Reply 객체 생성 및 설정
//        Reply reply = new Reply(newReply, sessionUser);
//
//
//
//        replyService.saveReply(reply);
//
//        return "redirect:/comm-detail/" + id;
    }
}
