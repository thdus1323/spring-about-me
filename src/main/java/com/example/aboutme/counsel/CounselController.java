package com.example.aboutme.counsel;

import com.example.aboutme._core.utils.RedisUtil;
import com.example.aboutme.counsel.CounselRequestRecord.CounselReqDTO;
import com.example.aboutme.counsel.CounselResponseRecord.CounselDTO.CounselDTORecord;
import com.example.aboutme.user.SessionUser;
import com.example.aboutme.voucher.enums.VoucherType;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class CounselController {
    private final CounselService counselService;
    private final RedisUtil redisUtil;
    private final RedisTemplate<String, Object> redisTemp;


    @GetMapping("/counsel")
    public String counselDetails(
            CounselReqDTO counselReqDTO,
                                 Model model) {

        if (VoucherType.fromKorean(counselReqDTO.voucherType()) == VoucherType.TEXT_THERAPY) {

            return "redirect:/chat/"+ counselReqDTO.reservationId(); // 상담 세부 정보 페이지로 이동}}

        } else if (VoucherType.fromKorean(counselReqDTO.voucherType()) == VoucherType.VIDEO_THERAPY) {

            return "화상테라피경로";
        } else {

            return "보이스테라피경로";
        }
    }

    //상담일정
    @GetMapping("/schedule")
    public String schedule(Model model) {
        SessionUser sessionUser = redisUtil.getSessionUser();
        log.info("로그인한 유저 {} ", sessionUser);
        CounselDTORecord counselDTORecord = counselService.findCounsel(sessionUser);
        model.addAttribute("counselList", counselDTORecord);

        return "expert/schedule";
    }

    //텍스트테라피 페이지
    @GetMapping("/chat/{reservationId}")
    public String therapy(@PathVariable("reservationId") Integer reservationId, Model model) {
        model.addAttribute("reservationId", reservationId);

        return "/client/text-chat";
    }

    //텍스트테라피 상담 업데이트
    @PostMapping("/chat/complete")
    public String therapyUpdate(@PathVariable("counselId") Integer counselId){
        SessionUser sessionUser = redisUtil.getSessionUser();
//        counselService.completeCounsel(counselId,sessionUser);

        return "redirect:/client/myPage";
    }

}
