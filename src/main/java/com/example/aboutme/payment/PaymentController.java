package com.example.aboutme.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class PaymentController {
    private final PaymentService paymentService;

    //전문가 칮기 - 결제하기
    @GetMapping("/client/findExpert/payment")
    public String findExpertPayment() {
        return "client/findExpert/payment";
    }
}
