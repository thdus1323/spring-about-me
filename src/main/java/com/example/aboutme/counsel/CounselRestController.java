package com.example.aboutme.counsel;

import com.example.aboutme.reservation.Reservation;
import com.example.aboutme.reservation.enums.ReservationStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CounselRestController {

    @PostMapping("/counsel/{id}")
    public ResponseEntity<?> deleteTempReservation(@PathVariable Integer reservationId) {


        return ResponseEntity.ok("Reservation cancelled");
    }
}
