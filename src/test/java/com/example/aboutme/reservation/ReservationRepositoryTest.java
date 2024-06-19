package com.example.aboutme.reservation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ReservationRepositoryTest {

    @Autowired
    ReservationRepository reservationRepository;


    @Test
    public void findAll_test(){
       //when
       List<Reservation> reservations = reservationRepository.findAll();
       // eye
        System.out.println("결과값 =-=-=-=-=-=-=-=-=-=-" + reservations);
    }



}
