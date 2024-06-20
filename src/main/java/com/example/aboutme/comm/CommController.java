package com.example.aboutme.comm;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CommController {
    private final CommService commService;

    @GetMapping("/comm/write")
    public String community() {
        return "comm/comm-write";
    }

    @GetMapping("/comm")
    public String community(HttpServletRequest request) {
        List<CommResponse.CommAndReplyDTO> commsWithReplyList = commService.findAllCommsWithReply();
        request.setAttribute("commsWithReplyList", commsWithReplyList);

        return "comm/comm-main";
    }
}
