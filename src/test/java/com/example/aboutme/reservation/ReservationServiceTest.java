package com.example.aboutme.reservation;

import com.example.aboutme._core.error.exception.Exception400;
import com.example.aboutme._core.utils.Formatter;
import com.example.aboutme.reservation.resrvationResponse.ReservationDetails.ReservationDetailsDTO;
import com.example.aboutme.schedule.Schedule;
import com.example.aboutme.schedule.ScheduleRepository;
import com.example.aboutme.voucher.Voucher;
import com.example.aboutme.voucher.VoucherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
class ReservationServiceTest {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private VoucherRepository voucherRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

    //테스트에서는 빈주입이 안되어서 직접 뉴~
    private Formatter formatter = new Formatter();

    @Test
    void getReservationDetails() {

        int voucherId = 2;
        int expertId = 21;
        Voucher voucher = voucherRepository.findById(voucherId).orElseThrow(() -> new Exception400("해당하는 이용권을 찾을 수 없습니다."));
        List<Schedule> schedules = scheduleRepository.findByExpertId(expertId);

        List<Reservation> reservations = reservationRepository.findByExpertId(expertId);
        // 가격 포맷
        String formattedPrice = formatter.number((int) voucher.getPrice());

        // 엔티티를 DTO로 변환
        ReservationDetailsDTO.VoucherDTO voucherDTO = new ReservationDetailsDTO.VoucherDTO(
                voucher.getId(),
                voucher.getVoucherType().getKorean(),
                voucher.getExpert().getId(),
                formattedPrice,
                voucher.getCount(),
                voucher.getDuration(),
                voucher.getImagePath(),
                voucher.getStartDate(),
                voucher.getCreatedAt().toLocalDateTime(),
                voucher.getUpdatedAt().toLocalDateTime()
        );
        System.out.println("voucherDTO = " + voucherDTO);

        List<ReservationDetailsDTO.ScheduleDTO> scheduleDTOs = schedules.stream()
                .map(schedule -> new ReservationDetailsDTO.ScheduleDTO(
                        schedule.getId(),
                        schedule.getExpert().getId(),
                        schedule.getStartTime(),
                        schedule.getEndTime(),
                        schedule.getRestType().getKorean(),
                        schedule.getStartDay().getKorean(),
                        schedule.getEndDay().getKorean(),
                        schedule.getSpecificDate(),
                        schedule.getLunchStartTime(),
                        schedule.getLunchEndTime()
                ))
                .toList();
        scheduleDTOs.forEach(scheduleDTO -> System.out.println("scheduleDTO = " + scheduleDTO));

        List<ReservationDetailsDTO.ReservationDTO> reservationDTOs = reservations.stream()
                .map(reservation -> new ReservationDetailsDTO.ReservationDTO(
                        reservation.getId(),
                        reservation.getExpert().getId(),
                        reservation.getClient().getId(),
                        reservation.getVoucher().getId(),
                        reservation.getStatus().name(),
                        reservation.getStartTime(),
                        reservation.getReservationDate(),
                        reservation.getSchedule().getId()
                ))
                .toList();
        reservationDTOs.forEach(reservationDTO -> System.out.println("reservationDTO = " + reservationDTO));

    }
}