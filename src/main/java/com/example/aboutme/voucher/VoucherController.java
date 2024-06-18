package com.example.aboutme.voucher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class VoucherController {
    private final VoucherService voucherService;

    //전문가 칮기 - 이용권
    @GetMapping("/client/findExpert/voucher")
    public String findExpertVoucher() {
        return "client/findExpert/voucher";
    }
}
