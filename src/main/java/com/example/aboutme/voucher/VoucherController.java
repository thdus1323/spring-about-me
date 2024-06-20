package com.example.aboutme.voucher;

import com.example.aboutme.voucher.VoucherResponseDTO.voucherList.VoucherListRecord;
import com.example.aboutme.voucher.VoucherResponseDTO.voucherList.VoucherRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class VoucherController {
    private final VoucherService voucherService;

    //전문가 칮기 - 이용권
    @GetMapping("/client/findExpert/voucher/{expertId}")
    public String findExpertVoucher(Model model, @PathVariable("expertId") Integer expertId) {
        VoucherListRecord voucherListRecord = voucherService.getVoucherListByExpertId(expertId);
        System.out.println("voucherListRecord = " + voucherListRecord);
        model.addAttribute("model", voucherListRecord);
        return "client/findExpert/voucher";
    }

    //이용권목록
    @GetMapping("/voucher-list")
    public String voucherList() {
        return "expert/voucher-list";
    }
}
