package com.example.aboutme.reservation;

import com.example.aboutme.reservation.enums.ReservationStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/client/reservations")
public class ReservationRestController {

    private final ReservationRepository reservationRepository;


    @PostMapping("/temp/{reservationId}")
    public ResponseEntity<?> deleteTempReservation(@PathVariable Integer reservationId) {
        log.info("Delete temporary reservation      " + reservationId);
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));

        if (reservation.getStatus() == ReservationStatus.PENDING) {
            reservationRepository.delete(reservation);
        }

        return ResponseEntity.ok("Reservation cancelled");
    }

    @PostMapping("/complete")
    public ResponseEntity<?> completeReservation(@RequestParam("reservationId") Integer reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));

        reservation.setStatus(ReservationStatus.COMPLETED);
        reservationRepository.save(reservation);

        return ResponseEntity.ok("Reservation completed");
    }
}
