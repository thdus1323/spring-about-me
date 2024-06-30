package com.example.aboutme.counsel;

import com.example.aboutme.alarm.sse.SseService;
import com.example.aboutme.counsel.enums.ReservationStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Slf4j
@RestController
public class CounselRestController {
    private final CounselRepository counselRepository;
    private final SseService sseService;

    @Autowired
    public CounselRestController(CounselRepository counselRepository, SseService sseService) {
        this.counselRepository = counselRepository;
        this.sseService = sseService;
    }
    @PostMapping("/client/reservations/temp/{reservationId}")
    public ResponseEntity<?> deleteTempReservation(@PathVariable("reservationId") Integer reservationId) {
        log.info("Delete temporary reservation      " + reservationId);
        Counsel reservation = counselRepository.findById(reservationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));

        if (reservation.getReservationStatus() == ReservationStatus.RESERVATION_PENDING) {
            counselRepository.delete(reservation);
        }

        return ResponseEntity.ok("Reservation cancelled");
    }



    @PatchMapping("/counsel/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable("id") Integer id, @RequestBody Map<String, String> request) {
        ReservationStatus status = ReservationStatus.valueOf(request.get("status"));
        Counsel counsel = counselRepository.findById(id).orElseThrow(() -> new RuntimeException("Counsel not found"));
        counsel.setReservationStatus(status);
        counselRepository.save(counsel);

        // 상태 변경에 따른 이벤트 전송
        if (status == ReservationStatus.RESERVATION_PENDING) {
            sseService.notifyExpert(counsel.getExpert().getId(), "예약신청이 도착했습니다.");
        } else if (status == ReservationStatus.RESERVATION_COMPLETED) {
            sseService.notifyClient(counsel.getClient().getId(), "예약이 확정되었습니다.");
        }

        return ResponseEntity.ok("Status updated");
    }
}
