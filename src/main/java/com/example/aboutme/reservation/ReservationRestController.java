package com.example.aboutme.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ReservationRestController {

    private final ReservationService reservationService;

    @PostMapping("/api/reservations/temp")
    public ResponseEntity<ReservationResponse> saveTempReservation(@RequestBody ReservationRequest reservationRequest) {
//        Reservation reservation = reservationService.createTempReservation(reservationRequest);
//        ReservationResponse response = new ReservationResponse(reservation.getId(), reservation.getStatus().getKorean());
//        return ResponseEntity.ok(response);
        return null;
    }
}
