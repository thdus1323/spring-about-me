package com.example.aboutme.counsel;

import com.example.aboutme._core.utils.RedisUtil;
import com.example.aboutme.counsel.CounselResponseRecord.CounselDTO.CounselDTORecord;
import com.example.aboutme.counsel.enums.ReservationStatus;
import com.example.aboutme.user.SessionUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CounselRestController {
    private final CounselRepository counselRepository;
    private final CounselService counselService;
    private final RedisUtil redisUtil;

    @PostMapping("/client/reservations/temp/{reservationId}")
    public ResponseEntity<?> deleteTempReservation(@PathVariable Integer reservationId) {
        log.info("Delete temporary reservation      " + reservationId);
        Counsel reservation = counselRepository.findById(reservationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));

        if (reservation.getReservationStatus() == ReservationStatus.RESERVATION_PENDING) {
            counselRepository.delete(reservation);
        }

        return ResponseEntity.ok("Reservation cancelled");
    }


    @GetMapping("/client/schedule")
    public ResponseEntity<Page<CounselDTORecord>> getSchedule(
            @RequestParam("userId") Integer userId,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            Model model) {
        SessionUser sessionUser = redisUtil.getSessionUser();
        Page<CounselDTORecord> schedules = counselService.pagingCounsel(sessionUser, page, size);

        model.addAttribute("schedules", schedules);

        return ResponseEntity.ok(schedules);
    }
}
