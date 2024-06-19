package com.example.aboutme.user;

import com.example.aboutme.comm.CommService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final CommService commService;
    private final HttpSession session;


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


    @PostMapping("/user-login")
    public String login(String email, String password) {
        System.out.println("email = " + email);
        System.out.println("password = " + password);
        return "redirect:/";
    }

    // 👻👻👻공통👻👻👻
    // 메인페이지
    @GetMapping("/")
    public String index(HttpServletRequest request) {
//        List<CommResponse.ClientMainCommListDTO> mainCommListDTOS = commService.getMainComms();
//        request.setAttribute("mainCommList", mainCommListDTOS);
//        System.out.println("이거 맞나? 기억ㄷ ㅗ안 ㅁㄹ어ㅣ남;ㅇ");
//        System.out.println(mainCommListDTOS);
        return "client/main";
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
