package com.example.aboutme.voucher;

import com.example.aboutme._core.utils.ApiUtil;
import com.example.aboutme._core.utils.RedisUtil;
import com.example.aboutme.user.SessionUser;
import com.example.aboutme.voucher.VoucherRequestDTO.VoucherSaveDTO;
import com.example.aboutme.voucher.VoucherResponseDTO.expertVouchers.ExpertVouchersRecord;
import com.example.aboutme.voucher.VoucherResponseDTO.voucherList.VoucherListRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/vouchers/save-form")
    public String voucherSaveForm() {
        return "expert/voucher-form";
    }

    @PostMapping("/vouchers/save")
    public String saveVoucher(@ModelAttribute VoucherSaveDTO request){
        SessionUser sessionUser = redisUtil.getSessionUser();
        voucherService.saveVoucher(request, sessionUser);
        return "redirect:/voucher-list";
    }
}
