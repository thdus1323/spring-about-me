package com.example.aboutme.payment;

import com.example.aboutme.payment.PaymentRequestRecord.PaymentPortOneReqDTO;
import com.example.aboutme.payment.PaymentResponseRecord.PaymentPortOneRespDTO;
import com.example.aboutme.payment.enums.PaymentStatus;
import com.example.aboutme.user.SessionUser;
import com.example.aboutme.user.User;
import com.example.aboutme.user.UserRepository;
import com.example.aboutme.voucher.Voucher;
import com.example.aboutme.voucher.VoucherRepository;
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

    //결제데이터 받아서 임시저장
    @Transactional
    public PaymentPortOneRespDTO requestPayment(PaymentPortOneReqDTO reqDTO, SessionUser sessionUser) {
        User client = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        Voucher voucher = voucherRepository.findById(reqDTO.voucherId())
                .orElseThrow(() -> new IllegalArgumentException("바우처를 찾을 수 없습니다."));

        Payment payment = Payment.builder()
                .amount(reqDTO.amount())
                .paymentMethod(reqDTO.paymentMethod())
                .client(client)
                .voucher(voucher)
                .status(PaymentStatus.PENDING)
                .merchantUid(reqDTO.merchantUid())
                .build();

        paymentRepository.save(payment);

        return new PaymentPortOneRespDTO(
                payment.getId(),
                payment.getImpUid(),
                payment.getMerchantUid(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                client.getName(),
                client.getPhone(),
                payment.getStatus().name()
        );
    }

    //임시 저장한 결제가 완료되면 'COMPLETED' 로 변경
    @Transactional
    public String completePayment(String impUid, String merchantUid) {
        Payment payment = paymentRepository.findByMerchantUid(merchantUid)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문 ID에 대한 결제 내역이 없습니다."));

        payment.setImpUid(impUid);
        payment.setStatus(PaymentStatus.COMPLETED);
        paymentRepository.save(payment);
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
                        payment.getPaymentMethod(),
                        payment.getClient().getName(),
                        payment.getClient().getPhone(),
                        payment.getStatus().name()
                ))
                .collect(Collectors.toList());
    }

}
