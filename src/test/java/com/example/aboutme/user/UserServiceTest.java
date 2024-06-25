package com.example.aboutme.user;

import com.example.aboutme._core.error.exception.Exception404;
import com.example.aboutme._core.utils.Formatter;
import com.example.aboutme.comm.CommRepository;
import com.example.aboutme.counsel.CounselRepository;
import com.example.aboutme.counsel.enums.CounselStateEnum;
import com.example.aboutme.payment.PaymentRepository;
import com.example.aboutme.reservation.ReservationRepository;
import com.example.aboutme.reservation.enums.ReservationStatus;
import com.example.aboutme.user.UserResponseRecord.UserProfileDTO;
import com.example.aboutme.voucher.Voucher;
import com.example.aboutme.voucher.VoucherRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
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
        List<UserProfileDTO.ReservationDTO> progressReservations = new ArrayList<>();
        List<UserProfileDTO.ReservationDTO> cancelReservations = new ArrayList<>();

        reservationRepository.findByClientId(id).stream()
                .forEach(r -> {
                    Voucher v = r.getVoucher();
                    Integer usedCount = counselRepository.findByClientIdAndState(id, CounselStateEnum.COMPLETED);
                    Integer remainingCount = v.getCount() - usedCount;
                    UserProfileDTO.ReservationDTO reservationDTO = UserProfileDTO.ReservationDTO.builder()
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
                            .count(usedCount)
                            .remainingCount(remainingCount)
                            .build();

                    if (r.getStatus() == ReservationStatus.SCHEDULED || r.getStatus() == ReservationStatus.COMPLETED) {
                        progressReservations.add(reservationDTO);
                    }
                    if (r.getStatus() == ReservationStatus.CANCELLED) {
                        cancelReservations.add(reservationDTO);
                    }
                });
        progressReservations.forEach(p -> System.out.println("progressReservations = " + p));
        cancelReservations.forEach(c -> System.out.println("cancelReservations = " + c));
//        List<UserProfileDTO.ReservationDTO> reservations = reservationRepository.findByClientId(id).stream()
//                .map(r -> new UserProfileDTO.ReservationDTO(
//                        r.getId(), r.getExpert().getId(), r.getClient().getId(), r.getVoucher().getId(),
//                        r.getSchedule().getId(), r.getStatus().getKorean(), r.getStartTime(), r.getReservationDate(),
//                        r.getDayOfWeek(), r.getCreatedAt(), r.getUpdatedAt()))
//                .toList();
//
//        List<UserProfileDTO.Comm> commPosts = commRepository.findByUserId(id).stream()
//                .map(c -> new UserProfileDTO.Comm(
//                        c.getId(), c.getContent(), c.getTitle(), c.getCategory().getKorean()))
//                .toList();
//
//        UserProfileDTO userProfileDTO =  new UserProfileDTO(user, vouchers, reservations, commPosts);
//        String json = new ObjectMapper().writeValueAsString(userProfileDTO);
//        System.out.println("json = " + json);
    }
}