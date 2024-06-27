package com.example.aboutme.counsel;

import com.example.aboutme.counsel.enums.ReservationStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CounselRestController {
    private final CounselRepository counselRepository;

    @PostMapping("/client/reservations/temp/{reservationId}")
    public ResponseEntity<?> deleteTempReservation(@PathVariable Integer reservationId) {
        log.info("Delete temporary reservation      " + reservationId);
        Counsel reservation = counselRepository.findById(reservationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));

        if (reservation.getReservationStatus() == ReservationStatus.PENDING) {
            counselRepository.delete(reservation);
        }

        return ResponseEntity.ok("Reservation cancelled");
    }


}
