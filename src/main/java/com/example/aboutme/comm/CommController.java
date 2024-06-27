package com.example.aboutme.comm;

import com.example.aboutme._core.utils.RedisUtil;
import com.example.aboutme.user.SessionUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class CommController {
    private final CommService commService;
    private final RedisUtil redisUtil;

    @GetMapping("/comm/write")
    public String communityWrite() {
        return "comm/comm-write";
    }

    // TODO : 수정 필요
    // 게시글 수정
    @PostMapping("/comm/update")
    public ResponseEntity<?> communityUpdate(@RequestParam CommRequest.UpdateRequestCommDTO updateRequestCommDTO) {
        SessionUser sessionUser = redisUtil.getSessionUser();
        commService.updateComm(sessionUser.getId(), updateRequestCommDTO);

        return ResponseEntity.ok("게시글 수정 완료");
    }

    // 글쓰기 완료 후 메인 페이지로
    @PostMapping("/comm/write-complete")
    public ResponseEntity<?> communityWriteComplete(@RequestBody CommRequest.RequestCommDTO commRequest) {
        SessionUser sessionUser = redisUtil.getSessionUser();
        commService.saveComm(sessionUser.getId(), commRequest);

        return ResponseEntity.ok("게시글 등록 완료");
    }

    @GetMapping("/comm-detail/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) throws JsonProcessingException {

        CommResponse.CommDetailDTO comm = commService.getCommDetail(id);
        
//        String json = new ObjectMapper().writeValueAsString(comm);
//        log.info("디테일  {}", json);

        model.addAttribute("comm", comm);

        return "comm/comm-detail";
    }

    // 전문답변이 있는지 확인
    @GetMapping("/api/comm-detail/{id}/has-expert-reply")
    public ResponseEntity<Boolean> hasExpertReply(@PathVariable("id") Integer id) {
        boolean hasExpertReply = commService.hasExpertReply(id);
        return ResponseEntity.ok(hasExpertReply);
    }

    @GetMapping("/comm")
    public String community(HttpServletRequest request) {

        List<CommResponse.ALLCommWithRepliesDTO> allCommsWithReplyList = commService.findAllCommWithReply();
        request.setAttribute("allCommsWithReplyList", allCommsWithReplyList);

        return "/comm/comm-main";
    }
}
