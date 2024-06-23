package com.example.aboutme.reservation;

import com.example.aboutme._core.error.exception.Exception400;
import com.example.aboutme._core.utils.Formatter;
import com.example.aboutme.reservation.enums.ReservationStatus;
import com.example.aboutme.reservation.resrvationResponse.ReservationDetailsDTO;
import com.example.aboutme.user.User;
import com.example.aboutme.user.UserRepository;
import com.example.aboutme.voucher.Voucher;
import com.example.aboutme.voucher.VoucherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ReservationServiceTest {

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private UserRepository userRepository;

    //테스트에서는 빈주입이 안되어서 직접 뉴~
    private Formatter formatter = new Formatter();

    @Test
    void getReservationDetails() {

        int voucherId = 2;
        int expertId = 21;
        Voucher voucher = voucherRepository.findById(voucherId).orElseThrow(() -> new Exception400("해당하는 이용권을 찾을 수 없습니다."));
        User user = userRepository.findById(expertId).orElseThrow(() -> new Exception400("해당하는 전문가를 찾을 수 없습니다."));

        // 가격 포맷
        String formattedPrice = formatter.number((int) voucher.getPrice());

        // 엔티티를 DTO로 변환
        ReservationDetailsDTO.VoucherDTO voucherDTO = new ReservationDetailsDTO.VoucherDTO(
                voucher.getId(),
                voucher.getVoucherType().getKorean(),
                voucher.getExpert().getId(),
                formattedPrice,
                voucher.getCount(),
                voucher.getDuration()
        );
        ReservationDetailsDTO.UserDTO userDTO = new ReservationDetailsDTO.UserDTO(
                user.getId(),
                user.getLevel().getKorean()
        );

        System.out.println("userDTO = " + userDTO);
        System.out.println("voucherDTO = " + voucherDTO);
    }

    @Test
    void createTempReservation() {
        int expertId = 21;
        int voucherId = 1;
        String startTime = "12:00";

        User expert = userRepository.findById(expertId)
                .orElseThrow(() -> new RuntimeException("전문가를 찾을 수 없습니다."));
        Voucher voucher = voucherRepository.findById(voucherId)
                .orElseThrow(() -> new RuntimeException("바우처를 찾을 수 없습니다."));

        Reservation reservation = new Reservation();
        reservation.setExpert(expert);
        reservation.setVoucher(voucher);
        reservation.setStartTime(startTime);
        String reservationDate = "1";
        reservation.setReservationDate(reservationDate);
        reservation.setStatus(ReservationStatus.PENDING);

        System.out.println("reservation = " + reservation);
    }
}