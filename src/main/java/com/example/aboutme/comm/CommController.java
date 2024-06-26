package com.example.aboutme.comm;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CommController {
    private final CommService commService;

    @GetMapping("/comm/write")
    public String communityWrite() {
        return "comm/comm-write";
    }

    @GetMapping("/comm-detail/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {

        CommResponse.CommWithRepliesDTO comm = commService.findByIdDetail(id);
        model.addAttribute("comm", comm);

        return "comm/comm-detail";
    }

    // 전문답변이 있는지 확인
    @GetMapping("/comm-detail/{id}/has-expert-reply")
    public boolean hasExpertReply(@PathVariable("id") Integer id) {
        return commService.hasExpertReply(id);
    }

    @GetMapping("/comm")
    public String community(HttpServletRequest request) {

        List<CommResponse.ALLCommWithRepliesDTO> allCommsWithReplyList = commService.findAllCommWithReply();
        request.setAttribute("allCommsWithReplyList", allCommsWithReplyList);

        return "/comm/comm-main";
    }
}
