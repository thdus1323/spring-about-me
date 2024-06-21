package com.example.aboutme.reservation;

import com.example.aboutme.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query("SELECT r FROM Reservation r WHERE r.expert.id = :expertId AND " +
            "DATE(r.reservationDate) = :date")
    List<Reservation> findByExpertIdAndDate(@Param("expertId") Integer expertId,
                                            @Param("date") LocalDate date);
}
