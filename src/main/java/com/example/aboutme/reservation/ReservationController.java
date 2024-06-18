package com.example.aboutme.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ReservationController {
    private final ReservationService reservationService;

    //전문가 칮기 - 예약하기
    @GetMapping("/client/findExpert/reservation")
    public String findExpertReservation() {
        return "client/findExpert/reservation";
    }
}
