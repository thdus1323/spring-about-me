package com.example.aboutme.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/join")
    public String index() {
        return "oauth/join";
    }

    @GetMapping("/login")
    public String login() {
        return "oauth/login";
    }

//    @GetMapping("/")
//    public String index() {
//        return "client/main";
//    }
//
//    @GetMapping("/client/findExpert/detail")
//    public String findExpertDetail() {
//        return "client/findExpert/detail";
//    }
//
//    @GetMapping("/client/findExpert")
//    public String findExpert() {
//        return "client/findExpert/main";
//    }
//
//    @GetMapping("/client/comm")
//    public String community() {
//        return "client/comm/comm-main";
//    }
//    @GetMapping("/client/findExpert/voucher")
//    public String findExpertVoucher() {
//        return "client/findExpert/voucher";
//    }

    // 👻👻👻공통👻👻👻
    // 메인페이지
//    @GetMapping("/")
//    public String expert() {
//        return "client/main";
//    }

    //TODO: 커뮤니티 페이지
    //커뮤니티 - 메인
    @GetMapping("/comm")
    public String community() {
        return "comm/comm-main";
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
