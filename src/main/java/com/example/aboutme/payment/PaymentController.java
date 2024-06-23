package com.example.aboutme.payment;

import com.example.aboutme.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class PaymentController {

    private final PaymentService paymentService;
    private final ReservationService reservationService;

    //전문가 칮기 - 결제하기
    @GetMapping("/client/findExpert/payment/{reservationId}")
    public String findExpertPayment(@PathVariable("reservationId") Integer reservationId) {
        reservationService.예약조회하기();
        return "client/findExpert/payment";
    }


}
