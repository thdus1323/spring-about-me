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

    @PostMapping("/client/reservations/complete")
    public ResponseEntity<?> completeReservation(
            @RequestParam("reservationId") Integer reservationId,
            @RequestParam("imp_uid") String impUid,
            @RequestParam("merchant_uid") String merchantUid,
            @RequestParam("paid_amount") int paidAmount,
            @RequestParam("apply_num") String applyNum) {
    
        log.info("Complete reservation      " + reservationId, "impUid " + impUid, "paid " + paidAmount, "apply " + applyNum);
        // 여기에 결제 내역을 저장하는 로직을 추가합니다.
        // 예: 결제 정보를 기반으로 결제 테이블에 저장

        return ResponseEntity.ok("결제 내역 저장 완료");
    }

//    @PostMapping("/complete")
//    public String completeReservation(@RequestParam("reservationId") Integer reservationId) {
//        log.info("Complete reservation      " + reservationId);
//        Reservation reservation = reservationRepository.findById(reservationId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));
//
//        reservation.setStatus(ReservationStatus.COMPLETED);
//        reservationRepository.save(reservation);
//
//        return "redirect:/";
//    }
}
