package com.example.aboutme.comm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class CommController {
    private final CommService commService;

    @GetMapping("/comm/write")
    public String communityWrite() {
        return "comm/comm-write";
    }

    @GetMapping("/comm-detail/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) throws JsonProcessingException {

        CommResponse.CommDetailDTO comm = commService.getCommDetail(id);
        String json = new ObjectMapper().writeValueAsString(comm);
        log.info("디테일  {}", json);
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
