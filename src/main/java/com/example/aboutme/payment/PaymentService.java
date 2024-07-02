package com.example.aboutme.payment;

import com.example.aboutme._core.utils.DayOfWeekConverter;
import com.example.aboutme.counsel.Counsel;
import com.example.aboutme.counsel.CounselRepository;
import com.example.aboutme.counsel.CounselRequestRecord.CompletePaymentAndCounselReqDTO;
import com.example.aboutme.counsel.CounselRequestRecord.PaymentPortOneReqDTO;
import com.example.aboutme.counsel.enums.CounselStatus;
import com.example.aboutme.counsel.enums.ReservationStatus;
import com.example.aboutme.payment.PaymentResponseRecord.PaymentPortOneRespDTO;
import com.example.aboutme.payment.enums.PaymentMethods;
import com.example.aboutme.payment.enums.PaymentStatus;
import com.example.aboutme.user.SessionUser;
import com.example.aboutme.user.User;
import com.example.aboutme.user.UserRepository;
import com.example.aboutme.voucher.Voucher;
import com.example.aboutme.voucher.VoucherRepository;
import com.example.aboutme.voucher.enums.VoucherType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final VoucherRepository voucherRepository;
    private final CounselRepository counselRepository;


    //결제데이터 받아서 임시저장
    @Transactional
    public PaymentPortOneRespDTO requestPayment(PaymentPortOneReqDTO reqDTO, SessionUser sessionUser) {
        User client = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        User expert = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        Voucher voucher = voucherRepository.findById(reqDTO.voucherId())
                .orElseThrow(() -> new IllegalArgumentException("바우처를 찾을 수 없습니다."));

        Payment payment = Payment.builder()
                .impUid(reqDTO.impUid())
                .amount(reqDTO.amount())
                .paymentMethod(PaymentMethods.fromKorean(reqDTO.paymentMethod()))
                .client(client)
                .expert(expert)
                .voucher(voucher)
                .paymentStatus(PaymentStatus.PENDING)
                .merchantUid(reqDTO.merchantUid())
                .voucherPrice(reqDTO.price())
                .voucherDuration(reqDTO.duration())
                .voucherType(VoucherType.fromKorean(reqDTO.voucherType()))
                .voucherCount(reqDTO.voucherCount())
                .build();

        paymentRepository.save(payment);

        return new PaymentPortOneRespDTO(
                payment.getId(),
                payment.getImpUid(),
                payment.getMerchantUid(),
                payment.getAmount(),
                payment.getPaymentMethod().getKorean(),
                client.getName(),
                client.getPhone(),
                payment.getPaymentStatus().getKorean()
        );
    }

    //임시 저장한 결제가 완료되면 'COMPLETED' 로 변경
    @Transactional
    public String completePayment(CompletePaymentAndCounselReqDTO reqDTO, SessionUser sessionUser) {
        Payment payment = paymentRepository.findByMerchantUid(reqDTO.merchantUid())
                .orElseThrow(() -> new IllegalArgumentException("해당 주문 ID에 대한 결제 내역이 없습니다."));
        User client = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        User expert = userRepository.findById(reqDTO.expertId())
                .orElseThrow(() -> new IllegalArgumentException("전문가를 찾을 수 없습니다."));
        Voucher voucher = voucherRepository.findById(reqDTO.voucherId())
                .orElseThrow(() -> new IllegalArgumentException("바우처를 찾을 수 없습니다."));
        Counsel counsel = counselRepository.findById(sessionUser.getId()).orElseThrow(() -> new IllegalArgumentException("해당 예약을 찾을 수 없습니다."));


        payment.setImpUid(reqDTO.impUid());
        payment.setPaymentStatus(PaymentStatus.COMPLETED);
        payment.setPaymentMethod(PaymentMethods.CREDIT_CARD);
        paymentRepository.save(payment);
        counsel.setCounselStatus(CounselStatus.COUNSEL_PENDING);

        // 예약 상태를 변경하는 로직 추가
        Counsel reservation = counselRepository.findById(reqDTO.reservationId())
                .orElseThrow(() -> new IllegalArgumentException("해당 주문 ID에 대한 예약 내역이 없습니다."));
        reservation.setReservationStatus(ReservationStatus.RESERVATION_COMPLETED);

        return "Payment completed: " + payment.getId();
    }

    //결제내역 뷰에 반환
    public List<PaymentPortOneRespDTO> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(payment -> new PaymentPortOneRespDTO(
                        payment.getId(),
                        payment.getImpUid(),
                        payment.getMerchantUid(),
                        payment.getAmount(),
                        payment.getPaymentMethod().getKorean(),
                        payment.getClient().getName(),
                        payment.getClient().getPhone(),
                        payment.getPaymentStatus().getKorean()
                ))
                .collect(Collectors.toList());
    }

}
