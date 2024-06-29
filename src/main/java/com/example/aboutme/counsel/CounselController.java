package com.example.aboutme.counsel;

import com.example.aboutme._core.utils.RedisUtil;
import com.example.aboutme.counsel.CounselRequestRecord.CounselReqDTO;
import com.example.aboutme.counsel.CounselRequestRecord.ReservationRepDTO;
import com.example.aboutme.counsel.CounselResponseRecord.CounselDTO.CounselDTORecord;
import com.example.aboutme.counsel.CounselResponseRecord.MakeReservationDetailsDTO;
import com.example.aboutme.counsel.CounselResponseRecord.ReservationDetailsDTO;
import com.example.aboutme.user.SessionUser;
import com.example.aboutme.voucher.enums.VoucherType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequiredArgsConstructor
@Controller
public class CounselController {
    private final CounselService counselService;
    private final RedisUtil redisUtil;


    //   전문가 칮기 - 예약하기
    @GetMapping("/client/findExpert/reservation")
    public String findExpertReservation(
            @RequestParam(name = "voucherId", required = false) Integer voucherId,
            @RequestParam(name = "expertId", required = false) Integer expertId,
            Model model) {
        ReservationDetailsDTO reservationDetailsDTO = counselService.getReservationDetails(voucherId, expertId);
        model.addAttribute("model", reservationDetailsDTO);
        return "client/findExpert/reservation";
    }


    @PostMapping("/client/reservations/temp")
    public String saveTempReservation(ReservationRepDTO reqDTO) {
        SessionUser sessionUser = redisUtil.getSessionUser();
        Counsel tempReservation = counselService.createTempReservation(reqDTO, sessionUser);
        return "redirect:/client/findExpert/payment/" + tempReservation.getId();
    }


    @GetMapping("/client/mypage/reservation")
    public String makeReservation(@RequestParam(name = "voucherId", required = false) Integer voucherId,
                                  @RequestParam(name = "paymentId", required = false) Integer paymentId,
                                  @RequestParam(name = "expertId", required = false) Integer expertId, Model model) {
        log.info("😊😊😊😊😊예약만들기  : {}, {},{}", voucherId, expertId, paymentId);
        MakeReservationDetailsDTO respDTO = counselService.getMakeReservationDetails(voucherId, expertId, paymentId);
        model.addAttribute("model", respDTO);
        return "client/makeReservation";
    }


    @PostMapping("/client/mypage/reservation")
    public String makeReservation(ReservationRepDTO reqDTO) {
        log.info("😊😊😊😊😊예약만들기  : {}", reqDTO);
        SessionUser sessionUser = redisUtil.getSessionUser();
        counselService.makeReservation(reqDTO, sessionUser);
        return "redirect:/client/mypage";
    }

    @GetMapping("/counsel")
    public String counselDetails(CounselReqDTO counselReqDTO,
                                 Model model) {
        if (VoucherType.fromKorean(counselReqDTO.voucherType()) == VoucherType.TEXT_THERAPY) {
            return "redirect:/chat/" + counselReqDTO.counselId(); // 상담 세부 정보 페이지로 이동}}
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


//
//    //텍스트테라피 상담 업데이트
//    @PostMapping("/chat/complete")
//    public String therapyUpdate(@PathVariable("counselId") Integer counselId){
//        SessionUser sessionUser = redisUtil.getSessionUser();
////        counselService.completeCounsel(counselId,sessionUser);
//
//        return "redirect:/client/mypage";
//    }

    //텍스트테라피 페이지
    @GetMapping("/chat/{counselId}")
    public String therapyText(@PathVariable(name = "counselId") Integer counselId, Model model) {
        model.addAttribute("counselId", counselId);

        return "client/text-chat";
    }

}
