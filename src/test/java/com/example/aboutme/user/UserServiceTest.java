package com.example.aboutme.user;

import com.example.aboutme._core.error.exception.Exception404;
import com.example.aboutme._core.utils.Formatter;
import com.example.aboutme.comm.CommRepository;
import com.example.aboutme.counsel.CounselRepository;
import com.example.aboutme.counsel.enums.ReservationStatus;
import com.example.aboutme.payment.PaymentRepository;
import com.example.aboutme.user.UserResponseRecord.UserProfileDTO;
import com.example.aboutme.voucher.Voucher;
import com.example.aboutme.voucher.VoucherRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@DataJpaTest
class UserServiceTest {

    private final Formatter formatter = new Formatter();
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CounselRepository counselRepository;
    @Autowired
    private CommRepository commRepository;
    @Autowired
    private VoucherRepository voucherRepository;
    @Autowired
    private PaymentRepository paymentRepository;


    @Test
    public void 마이페이지정보() {
        int id = 1;

        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 정보를 찾을 수 없습니다."));

        List<UserProfileDTO.ReservationDTO> getProgressReservations = counselRepository.findByClientId(id).stream()
                .filter(c -> c.getReservationStatus().equals(ReservationStatus.RESERVATION_SCHEDULED) || c.getReservationStatus().equals(ReservationStatus.RESERVATION_COMPLETED))
                .map(r -> {
                            Voucher v = r.getVoucher();
                            Integer usedCount = counselRepository.countCompletedCounselsByClientIdAndVoucherId(id, v.getId());
                            Integer reservationCountData = counselRepository.countReservationsBeforeDate(id, v.getId(), r.getId());
                            Integer reservationCount = usedCount + reservationCountData;

                            return UserProfileDTO.ReservationDTO.builder()
                                    .id(r.getId())
                                    .expertId(r.getExpert().getId())
                                    .clientId(r.getClient().getId())
                                    .voucherId(r.getVoucher().getId())
                                    .scheduleId(r.getSchedule().getId())
                                    .status(r.getReservationStatus().getKorean())
                                    .startTime(r.getCounselTime())
                                    .reservationDate(r.getCounselDate())
                                    .dayOfWeek(r.getDayOfWeek())
                                    .createdAt(r.getCreatedAt())
                                    .updatedAt(r.getUpdatedAt())
                                    .voucherType(v.getVoucherType().getKorean())
                                    .voucherCount(v.getCount())
                                    .reservationCount(reservationCount)
                                    .build();
                        }

                ).toList();
        getProgressReservations.forEach(reservationDTO -> System.out.println("reservationDTO = " + reservationDTO));
    }

    @Test
    public void 이용권() {
        int id = 1;

        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 정보를 찾을 수 없습니다."));

        List<UserProfileDTO.VoucherDTO> getVouchers = paymentRepository.findByClientId(id).stream()
                .map(p -> {
                    Integer counselCount = counselRepository.findByClientIdAndStateCount(id, p.getId());
                    System.out.println("counselCount = " + counselCount);
                    Integer reservationCount = counselRepository.countByClientIdAndVoucherIdAndReservationId(id, p.getVoucher().getId(), p.getId());
                    System.out.println("reservationCount = " + reservationCount);
                    Integer remainingCount = p.getVoucherCount() - (reservationCount + counselCount);
                    System.out.println("remainingCount = " + remainingCount);

                    return UserProfileDTO.VoucherDTO.builder()
                            .id(p.getId())
                            .paymentId(p.getId())
                            .voucherType(p.getVoucherType().getKorean())
                            .clientId(p.getClient().getId())
                            .expertId(p.getExpert().getId())
                            .voucherId(p.getVoucher().getId())
                            .price(Formatter.number((int) p.getVoucherPrice()))
                            .count(p.getVoucherCount())
                            .remainingCount(remainingCount)
                            .counselCount(counselCount)
                            .duration(p.getVoucherDuration())
                            .createdAt(Formatter.formatTimestamp(p.getCreatedAt()))
                            .updatedAt(Formatter.formatTimestamp(p.getUpdatedAt()))
                            .build();
                }).collect(Collectors.toList());
        getVouchers.forEach(voucherDTO -> System.out.println("voucherDTO = " + voucherDTO));
    }

//    @Test
//    public void 커뮤니티() {
//        int id = 1;
//
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new Exception404("해당 정보를 찾을 수 없습니다."));
//
//        List<UserProfileDTO.Comm> getCommPosts =
//             commRepository.findByUserId(id).stream()
//                    .map(c -> new UserProfileDTO.Comm(
//                            c.getId(), c.getUser().getName(), c.getContent(), c.getTitle(), c.getCategory().getKorean()))
//                    .toList();
//
//        getCommPosts.forEach(voucherDTO -> System.out.println("voucherDTO = " + voucherDTO));
//    }
}



