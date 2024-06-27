package com.example.aboutme.user;

import com.example.aboutme._core.error.exception.Exception404;
import com.example.aboutme._core.utils.Formatter;
import com.example.aboutme.comm.CommRepository;
import com.example.aboutme.counsel.CounselRepository;
import com.example.aboutme.payment.PaymentRepository;
import com.example.aboutme.reservation.ReservationRepository;
import com.example.aboutme.user.UserResponseRecord.UserProfileDTO;
import com.example.aboutme.voucher.Voucher;
import com.example.aboutme.voucher.VoucherRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
class UserServiceTest {
    private final Formatter formatter = new Formatter();
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CommRepository commRepository;
    @Autowired
    private VoucherRepository voucherRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private CounselRepository counselRepository;

    @Test
    void 마이페이지정보() throws JsonProcessingException {
        int id = 1;

        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 정보를 찾을 수 없습니다."));

        List<UserProfileDTO.ReservationDTO> progressReservations = reservationRepository.findByClientId(id).stream()
                .map(r -> {
                    Voucher v = r.getVoucher();
                    Integer usedCount = counselRepository.countCompletedCounselsByClientIdAndVoucherId(id, v.getId());
                    Integer reservationCountData = reservationRepository.countReservationsBeforeDate(id, v.getId(), r.getId());
                    Integer reservationCount = usedCount + reservationCountData;

                    return UserProfileDTO.ReservationDTO.builder()
                            .id(r.getId())
                            .expertId(r.getExpert().getId())
                            .clientId(r.getClient().getId())
                            .voucherId(r.getVoucher().getId())
                            .scheduleId(r.getSchedule().getId())
                            .status(r.getStatus().getKorean())
                            .startTime(r.getStartTime())
                            .reservationDate(r.getReservationDate())
                            .dayOfWeek(r.getDayOfWeek())
                            .createdAt(r.getCreatedAt())
                            .updatedAt(r.getUpdatedAt())
                            .voucherType(v.getVoucherType().getKorean())
                            .voucherCount(v.getCount())
                            .reservationCount(reservationCount) // 예약 횟수
                            .build();
                }).toList();

        for (UserProfileDTO.ReservationDTO progressReservation : progressReservations) {
            System.out.println("progressReservation = " + progressReservation);
        }


    }
}