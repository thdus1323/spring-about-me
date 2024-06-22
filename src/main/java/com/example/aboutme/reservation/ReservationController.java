package com.example.aboutme.reservation;

import com.example.aboutme.reservation.resrvationResponse.ReservationListDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ReservationController {
    private final ReservationService reservationService;



//    전문가 칮기 - 예약하기
//    @GetMapping("/client/findExpert/reservation/")
//    public String findExpertReservation(
//            @RequestParam(name = "voucherId", required = false) Integer voucherId,
//            @RequestParam(name = "expertId", required = false) Integer expertId,
//            Model model) throws JsonProcessingException {
//
//        List<ReservationListDTO> reservationListDTOS = reservationService.getExpertSchedules(expertId,voucherId);
//        String modelJson = new ObjectMapper().writeValueAsString(reservationListDTOS);
//        System.out.println("modelJson = " + modelJson);
//        model.addAttribute("model", modelJson);
//        return "client/findExpert/reservation";
//    }


}
