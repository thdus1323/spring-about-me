package com.example.aboutme.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class ReservationController {
    private final ReservationService reservationService;


    //    전문가 칮기 - 예약하기
    @GetMapping("/client/findExpert/reservation")
    public String findExpertReservation(
            @RequestParam(name = "voucherId", required = false) Integer voucherId,
            @RequestParam(name = "expertId", required = false) Integer expertId,
            Model model) {

        model.addAttribute("voucherId", voucherId);
        model.addAttribute("expertId", expertId);


        return "client/findExpert/reservation";
    }


}
