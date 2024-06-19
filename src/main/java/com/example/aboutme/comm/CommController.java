package com.example.aboutme.comm;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class CommController {
    private final CommService commService;

    @GetMapping("/comm/write")
    public String community() {
        return "comm/comm-write";
    }

    @GetMapping("/comm-detail/{commId}")
//    @GetMapping("/comm-detail")
    public String detail(@PathVariable Integer commId, HttpServletRequest request) {
        CommResponse.CommDetailDTO comm = commService.getCommDetail(commId);
        request.setAttribute("comm", comm);
        return "comm/comm-detail";
    }

    //게시판 상세보기


}
