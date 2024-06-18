package com.example.aboutme.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

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

    // 메인페이지
    @GetMapping("/")
    public String expert() {
        return "client/main";
    }

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


}
