package com.example.aboutme.voucher;

import com.example.aboutme._core.utils.RedisUtil;
import com.example.aboutme.user.SessionUser;
import com.example.aboutme.voucher.VoucherResponseDTO.expertVouchers.ExpertVouchersRecord;
import com.example.aboutme.voucher.VoucherResponseDTO.voucherList.VoucherListRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class VoucherController {
    private final VoucherService voucherService;
    private final RedisUtil redisUtil;

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
    public String voucherList(Model model) {
        SessionUser sessionUser = redisUtil.getSessionUser();
        ExpertVouchersRecord vouchersRecord = voucherService.getExpertVouchersByExpertId(sessionUser.getId());
        System.out.println(vouchersRecord);
        model.addAttribute("model", vouchersRecord);
        return "expert/voucher-list";
    }

    @GetMapping("/vouchers/regi-form")
    public String addVoucher(Model model) {
        SessionUser sessionUser = redisUtil.getSessionUser();
        model.addAttribute("expertId", sessionUser.getId());

        return "expert/voucher-form";
    }
}
