package com.example.aboutme.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class PaymentController {
    private final PaymentService paymentService;
}
