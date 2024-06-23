package com.example.aboutme.payment;

import com.example.aboutme.payment.PaymentResponseRecord.PaymentDetailsDTO;
import com.example.aboutme.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class PaymentController {

    private final PaymentService paymentService;
    private final ReservationService reservationService;

    //전문가 칮기 - 결제하기
    @GetMapping("/client/findExpert/payment/{reservationId}")
    public String findExpertPayment(@PathVariable("reservationId") Integer reservationId, Model model) {
        PaymentDetailsDTO paymentDetailsDTO = reservationService.getTempReservation(reservationId);
        model.addAttribute("model", paymentDetailsDTO);
        return "client/findExpert/payment";
    }


}
