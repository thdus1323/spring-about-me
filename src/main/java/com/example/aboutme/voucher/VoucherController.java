package com.example.aboutme.voucher;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class VoucherController {
    private final VoucherService voucherService;
}
