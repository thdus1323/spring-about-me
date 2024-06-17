package com.example.aboutme;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckController {

    // expert
    @GetMapping("/expert/detail")
    public String expertDetail() {
        return "expert/detail";
    }

    @GetMapping("/expert/main")
    public String expertMain() {
        return "expert/main";
    }

    @GetMapping("/expert/mypage")
    public String expertMypage() {
        return "expert/mypage";
    }

    @GetMapping("/expert/payment")
    public String expertPayment() {
        return "expert/payment";
    }

    @GetMapping("/expert/reservation")
    public String expertReservation() {
        return "expert/reservation";
    }

    @GetMapping("/expert/voucher")
    public String expertVoucher() {
        return "expert/voucher";
    }

    @GetMapping("/expert/voucher-form")
    public String expertVoucherForm() {
        return "expert/voucher-form";
    }

    @GetMapping("/expert/voucher-list")
    public String expertVoucherList() {
        return "expert/voucher-list";
    }

    // user
    @GetMapping("/user/main")
    public String userMain() {
        return "user/main";
    }

    @GetMapping("/user/mypage")
    public String userMypage() {
        return "user/mypage";
    }
}
