package com.example.aboutme.user;

import jakarta.servlet.http.HttpSession;
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


    @GetMapping("/")
    public String expert() {
        return "client/main";
    }
}
