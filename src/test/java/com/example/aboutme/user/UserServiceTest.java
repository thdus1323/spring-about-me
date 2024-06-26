package com.example.aboutme.user;

import com.example.aboutme._core.error.exception.Exception404;
import com.example.aboutme._core.utils.Formatter;
import com.example.aboutme.comm.CommRepository;
import com.example.aboutme.counsel.Counsel;
import com.example.aboutme.counsel.CounselRepository;
import com.example.aboutme.counsel.enums.CounselStateEnum;
import com.example.aboutme.payment.PaymentRepository;
import com.example.aboutme.reservation.ReservationRepository;
import com.example.aboutme.reservation.enums.ReservationStatus;
import com.example.aboutme.user.UserResponseRecord.UserProfileDTO;
import com.example.aboutme.voucher.Voucher;
import com.example.aboutme.voucher.VoucherRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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


        List<UserProfileDTO.CounselDTO> completedCounsels = counselRepository.findByClientIdAndState(id, CounselStateEnum.COMPLETED).stream()
                .map(c -> {
                            Voucher v = c.getVoucher();
                    Integer counselCount = counselRepository.findByClientIdAndStateCount(id, CounselStateEnum.COMPLETED);

                    Integer aaa = counselRepository
                            .countByClientIdAndVoucherIdAndBeforeDate(id, v.getId(), c.getCounselDate());


                    return UserProfileDTO.CounselDTO.builder()
                                    .id(c.getId())
                                    .expertId(c.getExpert().getId())
                                    .clientId(c.getClient().getId())
                                    .voucherId(c.getVoucher().getId())
                                    .counselDate(c.getCounselDate().toString())
                                    .state(c.getState().toString())
                                    .createdAt(c.getCreatedAt().toString())
                                    .updatedAt(c.getUpdatedAt().toString())
                                    .voucherType(v.getVoucherType().getKorean())
                                    .build();
                        }
                )
                .toList();


        completedCounsels.forEach(counselDTO -> System.out.println("counselDTO = " + counselDTO));

    }
}