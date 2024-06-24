package com.example.aboutme.comm;

import com.example.aboutme.user.User;
import com.example.aboutme.user.enums.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public String detail(@PathVariable("id") Integer id, HttpServletRequest request, Model model) {
        // 세션에서 사용자 정보 가져오기
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("sessionUser");

        CommResponse.CommDetailDTO comm = commService.getCommDetail(id);
//        request.setAttribute("comm", comm);

        // userRole이 EXPERT인 경우에는 true, 그 외에는 false
        boolean isUserRole = user.getUserRole() == UserRole.EXPERT;

        model.addAttribute("comm", comm);
        model.addAttribute("isUserRole", isUserRole);

        return "comm/comm-detail";
    }

    @GetMapping("/comm")
    public String community(HttpServletRequest request) {
        List<CommResponse.CommAndReplyDTO> commsWithReplyList = commService.findAllCommsWithReply();

        // 필터링 예시: 고유한 Comm에 대해 하나의 DTO만 추가하기
        CommResponse.UniqueCommAndReplyDTOFilter filter = new CommResponse.UniqueCommAndReplyDTOFilter();
        List<CommResponse.CommAndReplyDTO> filteredList = filter.filterUnique(commsWithReplyList);

        request.setAttribute("filteredList", filteredList);

        return "comm/comm-main";
    }
}
