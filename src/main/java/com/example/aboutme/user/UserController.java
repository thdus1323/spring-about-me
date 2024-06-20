package com.example.aboutme.user;

import com.example.aboutme.comm.CommService;
import com.example.aboutme.user.enums.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final CommService commService;
    private final HttpSession session;
    private final DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration;


    @GetMapping("/join")
    public String joinForm() {
        return "oauth/join";
    }


//    //회원가입
//    @PostMapping("/join")
//    public String Join(UserRequest.JoinDTO reqDTO) {
//        System.out.println("reqDTO = " + reqDTO);
//    userService.joinByEmail(reqDTO);
//    return "redirect:/login";
//    }

    @GetMapping("/login")
    public String loginForm() {
        return "oauth/login";
    }


    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDTO) {
        User sessionUser = userService.loginByName(reqDTO);
        System.out.println("sessionUser = " + sessionUser);
        session.setAttribute("sessionUser", sessionUser);
        if (sessionUser.getUserRole() == UserRole.CLIENT) {
            return "redirect:/";
        } else if (sessionUser.getUserRole() == UserRole.EXPERT) {
            return "redirect:/expert/main";
        } else {
            return "oauth/login";
        }
    }


    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

    // 👻👻👻공통👻👻👻
    // 클라이언트 메인페이지
    @GetMapping("/")
    public String index(HttpServletRequest request) {
        HashMap<String, Object> clientMain = userService.getClientMain();
        request.setAttribute("clientMain", clientMain);
        System.out.println(clientMain);
        return "client/main";
    }

    // 익스퍼트 메인페이지
    @GetMapping("/expert/main")
    public String expertMain(HttpServletRequest request) {
        HashMap<String, Object> clientMain = userService.getClientMain();
        request.setAttribute("clientMain", clientMain);
        System.out.println(clientMain);
        return "expert/main";
    }


    //TODO: 커뮤니티 페이지
    //커뮤니티 - 메인
    @GetMapping("/comm")
    public String community() {
        return "comm/comm-main";
    }

    @GetMapping("comm/detail")
    public String communityDetail() {
        return "comm/comm-detail";
    }


    // 🐯🐯🐯Client🐯🐯🐯
    //전문가 찾기 - 메인
    @GetMapping("/client/findExpert")
    public String findExpert() {

        List<UserResponse.ExpertUserDTO> expertUserList = userService.getAllExpertUsers();
        session.setAttribute("expertUserList", expertUserList);

        return "client/findExpert/main";
    }

    //전문가 찾기 - 상세보기
    @GetMapping("/client/findExpert/detail")
    public String findExpertDetail() {
        return "client/findExpert/detail";
    }

    //클라이언트 - 마이페이지
    @GetMapping("/client/mypage")
    public String clientMypage() {
        return "client/mypage";
    }


    // 🩺🩺🩺expert🩺🩺🩺
}
