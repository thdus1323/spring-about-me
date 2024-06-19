package com.example.aboutme.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;




    @Transactional
    public void Reservation (Reservation reservation) {
        // 인증처리

        // 조건처리

        // 리스트
        List<Reservation> reservations = reservationRepository.findAll();

    }

}
