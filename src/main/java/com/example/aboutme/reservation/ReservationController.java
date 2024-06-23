package com.example.aboutme.reservation;

import com.example.aboutme.reservation.reservationRequest.ReservationTempRepDTO;
import com.example.aboutme.reservation.resrvationResponse.ReservationDetailsDTO;
import com.example.aboutme.user.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class ReservationController {
    private final ReservationService reservationService;
    private final RedisTemplate<String, Object> redisTemp;


    //    전문가 칮기 - 예약하기
    @GetMapping("/client/findExpert/reservation")
    public String findExpertReservation(
            @RequestParam(name = "voucherId", required = false) Integer voucherId,
            @RequestParam(name = "expertId", required = false) Integer expertId,
            Model model) {
        ReservationDetailsDTO reservationDetailsDTO = reservationService.getReservationDetails(voucherId, expertId);
        model.addAttribute("model", reservationDetailsDTO);
        return "client/findExpert/reservation";
    }


    @PostMapping("/client/reservations/temp")
    public String saveTempReservation(ReservationTempRepDTO reqDTO,
                                      Model model) {
        SessionUser sessionUser = (SessionUser) redisTemp.opsForValue().get("sessionUser");
        Reservation tempReservation = reservationService.createTempReservation(reqDTO, sessionUser);
        return "redirect:/client/findExpert/payment/" + tempReservation.getId();
    }

}
