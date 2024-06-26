package com.example.aboutme.reply;

import com.example.aboutme.comm.Comm;
import com.example.aboutme.comm.CommRepository;
import com.example.aboutme.comm.ResourceNotFoundException.ResourceNotFoundException;
import com.example.aboutme.user.User;
import com.example.aboutme.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class ReplyController {
    private final ReplyService replyService;
    private final CommRepository commRepository;
    private final UserRepository userRepository;

    // 전문가 댓글 달기
    @PostMapping("/reply/complete")
    public String complete(@RequestBody ReplyResponse.ReplyDataDTO newReply, HttpSession session) {
        // 세션에서 SessionUser 객체 가져오기
        User user = (User) session.getAttribute("sessionUser");
        System.out.println("유저 상태" + user.getUserRole());

        // newReply에서 가져온 아이디로 커뮤니티 조회
        Comm comm = commRepository.findById(Integer.valueOf(newReply.getId())).
                orElseThrow(() -> new ResourceNotFoundException("Comm not found with id " + newReply.getId()));

        // Reply 객체 생성 및 설정
//        Reply reply = new Reply(user, comm, newReply);

//        replyService.saveReply(reply);

        return "redirect:/comm-detail/" + comm.getId();
    }
}
