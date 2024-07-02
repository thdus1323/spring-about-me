package com.example.aboutme.payment;

import com.example.aboutme._core.utils.RedisUtil;
import com.example.aboutme.counsel.CounselRequestRecord.CompletePaymentAndCounselReqDTO;
import com.example.aboutme.counsel.CounselRequestRecord.PaymentPortOneReqDTO;
import com.example.aboutme.counsel.CounselService;
import com.example.aboutme.payment.PaymentResponseRecord.PaymentDetailsDTO;
import com.example.aboutme.payment.PaymentResponseRecord.PaymentPortOneRespDTO;
import com.example.aboutme.user.SessionUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
public class PaymentController {

    private final PaymentService paymentService;
    private final CounselService counselService;
    private final RedisTemplate<String, Object> redisTemp;
    private final RedisUtil redisUtil;


    //전문가 칮기 - 결제하기
    @GetMapping("/client/findExpert/payment/{reservationId}")
    public String findExpertPayment(@PathVariable("reservationId") Integer reservationId, Model model) {
        PaymentDetailsDTO paymentDetailsDTO = counselService.getTempReservation(reservationId);
        model.addAttribute("model", paymentDetailsDTO);
        return "client/findExpert/payment";
    }


    @PostMapping("/payments/request")
    @ResponseBody
    public PaymentPortOneRespDTO requestPayment(@RequestBody PaymentPortOneReqDTO paymentRequestDTO) {
        SessionUser sessionUser = redisUtil.getSessionUser();
        log.info("Requesting payment" + paymentRequestDTO);
        return paymentService.requestPayment(paymentRequestDTO, sessionUser);
    }


    @PostMapping("/payments/complete")
    public ResponseEntity<String> completePayment(CompletePaymentAndCounselReqDTO reqDTO) {
        log.info("결제완료됨 {}", reqDTO);
        SessionUser sessionUser = redisUtil.getSessionUser();
        paymentService.completePayment(reqDTO, sessionUser);
        return ResponseEntity.ok("/");
    }
//
//    @PostMapping("/client/mypage/cancelPayment")
//    public String cancelPayment(@RequestParam("impUid") String impUid, Model model) {
//        System.out.println("impUid = " + impUid);
//        try {
//            Map<String, Object> cancelResponse = paymentService.processCancellation(impUid);
//            model.addAttribute("cancelResponse", cancelResponse);
//            return "redirect:/client/mypage";  // 결제 취소 성공 페이지로 이동
//        } catch (Exception e) {
//            model.addAttribute("errorMessage", e.getMessage());
//            return "redirect:/client/mypage";  // 결제 취소 실패 페이지로 이동
//        }
//    }


    @GetMapping("/list")
    public String getAllPayments(Model model) {
        log.info("Getting all payments", model);
        List<PaymentPortOneRespDTO> respDTOS = paymentService.getAllPayments();
        model.addAttribute("model", respDTOS);
        return null;
    }

}
