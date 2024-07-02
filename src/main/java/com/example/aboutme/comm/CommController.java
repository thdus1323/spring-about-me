package com.example.aboutme.comm;

import com.example.aboutme._core.utils.RedisUtil;
import com.example.aboutme.comm.enums.CommCategory;
import com.example.aboutme.user.SessionUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    // 게시글 삭제
    @DeleteMapping("/comm/delete/{commId}")
    public ResponseEntity<?> deleteComm(@PathVariable("commId") Integer commId) {
        commService.deleteComm(commId);
        return ResponseEntity.ok("게시글 삭제 성공");
    }

    // 게시글 수정
    @PostMapping("/comm/update")
    public ResponseEntity<?> communityUpdate(@RequestBody CommRequest.UpdateRequestCommDTO updatedPost) {
        SessionUser sessionUser = redisUtil.getSessionUser();
        commService.updateComm(sessionUser.getId(), updatedPost);
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
        model.addAttribute("comm", comm);
        return "comm/comm-detail";
    }

    // 전문답변이 있는지 확인
    @GetMapping("/api/comm-detail/{id}/has-expert-reply")
    public ResponseEntity<Boolean> hasExpertReply(@PathVariable("id") Integer id) {
        boolean hasExpertReply = commService.hasExpertReply(id);
        return ResponseEntity.ok(hasExpertReply);
    }

    // 메인 페이지
    @GetMapping("/comm")
    public String community(@RequestParam(name = "page", defaultValue = "0") int page,
                            @RequestParam(name = "size", defaultValue = "9") int size,
                            @RequestParam(name = "category", required = false) CommCategory category,
                            Model model) {

        Pageable pageable = PageRequest.of(page, size);
        CommResponse.ALLCommWithRepliesPageDTO allCommsWithReplyList = commService.findAllCommWithReply(pageable, category);
        model.addAttribute("allCommsWithReplyList", allCommsWithReplyList);
        return "comm/comm-main";

    }


}
