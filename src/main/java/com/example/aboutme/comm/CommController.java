package com.example.aboutme.comm;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
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
    public String detail(@PathVariable Integer id, HttpServletRequest request) {
        CommResponse.CommDetailDTO comm = commService.getCommDetail(id);
        request.setAttribute("comm", comm);
        System.out.println("comm = " + comm);
        return "comm/comm-detail";
    }

    @GetMapping("/comm")
    public String community(HttpServletRequest request) {
        List<CommResponse.CommAndReplyDTO> commsWithReplyList = commService.findAllCommsWithReply();
        request.setAttribute("commsWithReplyList", commsWithReplyList);

        return "comm/comm-main";
    }
}
