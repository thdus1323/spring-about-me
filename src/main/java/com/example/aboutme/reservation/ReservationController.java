package com.example.aboutme.reservation;

import com.example.aboutme.reservation.resrvationResponse.ReservationListDTO;
import com.example.aboutme.schedule.Schedule;
import com.example.aboutme.schedule.ScheduleRepository;
import com.example.aboutme.schedule.ScheduleResponse.AvailableTimeRecord;
import com.example.aboutme.schedule.ScheduleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Controller
public class ReservationController {
    private final ReservationService reservationService;
    private final ScheduleService scheduleService;



//    전문가 칮기 - 예약하기
    @GetMapping("/client/findExpert/reservation/")
    public String findExpertReservation(
            @RequestParam(name = "voucherId", required = false) Integer voucherId,
            @RequestParam(name = "expertId", required = false) Integer expertId,
            Model model) throws JsonProcessingException {

        List<ReservationListDTO> reservationListDTOS = reservationService.getExpertSchedules(expertId,voucherId);
        String modelJson = new ObjectMapper().writeValueAsString(reservationListDTOS);
        System.out.println("modelJson = " + modelJson);
        model.addAttribute("model", modelJson);
        return "client/findExpert/reservation";
    }


}
