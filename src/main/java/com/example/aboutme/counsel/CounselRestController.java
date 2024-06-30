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
@RequiredArgsConstructor
public class CounselRestController {
    private final CounselRepository counselRepository;
    private final CounselService counselService;

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
        counselService.updateCounselStatus(id, status);
        return ResponseEntity.ok("Status updated");
    }
}
