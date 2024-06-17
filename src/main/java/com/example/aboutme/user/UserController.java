package com.example.aboutme.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/")
    public String index() {
        return "client/main";
    }

    @GetMapping("/client/findExpert/detail")
    public String findExpertDetail() {
        return "client/findExpert/detail";
    }

    @GetMapping("/client/findExpert")
    public String findExpert() {
        return "client/findExpert/main";
    }

    @GetMapping("/client/comm")
    public String community() {
        return "client/comm/comm-main";
    }
    @GetMapping("/client/findExpert/voucher")
    public String findExpertVoucher() {
        return "client/findExpert/voucher";
    }




}
