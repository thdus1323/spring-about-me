package com.example.aboutme.reply;

import com.example.aboutme._core.utils.RedisUtil;
import com.example.aboutme.comm.CommRepository;
import com.example.aboutme.user.SessionUser;
import com.example.aboutme.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class ReplyController {
    private final ReplyService replyService;
    private final CommRepository commRepository;
    private final UserRepository userRepository;
    private final RedisUtil redisUtil;
    private final ReplyRepository replyRepository;

    // 전문가 댓글 달기
    @PostMapping("/expert-reply/complete")
    public ResponseEntity<?> complete(@RequestBody ReplyResponse.ReplyDataDTO newReply) {
        SessionUser sessionUser = redisUtil.getSessionUser();
        replyService.saveExpertReply(newReply, sessionUser.getId());

        return ResponseEntity.ok("댓글이 성공적으로 저장되었습니다.");
    }

    // 일반인 댓글 달기
    @PostMapping("/client-reply/complete")
    public ResponseEntity<?> complete(@RequestBody ReplyResponse.ClientReplyDataDTO newReply) {
        SessionUser sessionUser = redisUtil.getSessionUser();
        replyService.saveClientReply(newReply, sessionUser.getId());


        return ResponseEntity.ok("댓글이 성공적으로 저장되었습니다.");
    }
}
