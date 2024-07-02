package com.example.aboutme.payment;

import com.example.aboutme._core.config.PortOneConfig;
import com.example.aboutme._core.error.exception.Exception404;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final VoucherRepository voucherRepository;
    private final CounselRepository counselRepository;
    private final PortOneConfig portOneConfig;

    private final RestTemplate restTemplate = new RestTemplate();

//    public void cancelPayment(String impUid, SessionUser sessionUser) {
//        Payment payment = paymentRepository.findByImpUid(impUid);
//        System.out.println("payment = " + payment);
//
//        if (payment.getImpUid().equals(impUid)) {
//            String token = getToken();
//            System.out.println("token = " + token);
//            Map<String, Object> cancelResponse = cancelPayment(token, payment.getImpUid());
//            System.out.println("결제 취소 응답 본문: " + cancelResponse);
//            if (cancelResponse != null) {
//                payment.setPaymentStatus(PaymentStatus.REFUNDED);
//            }
//
//        } else {
//            throw new RuntimeException("impUid 불일치");
//        }
//    }

    @Transactional
    public Map<String, Object> cancelPayment(String impUid, SessionUser sessionUser) {
        Payment payment = paymentRepository.findByImpUid(impUid);


        if (!payment.getClient().getId().equals(sessionUser.getId())) {
            throw new RuntimeException("결제 취소 권한이 없습니다.");
        }

        String token = getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/json;charset=utf-8");
        headers.add("Authorization", "Bearer " + token);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode body = mapper.createObjectNode();
        body.put("imp_uid", impUid);

        HttpEntity<String> request = new HttpEntity<>(body.toString(), headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                "https://api.iamport.kr/payments/cancel",
                HttpMethod.POST,
                request,
                Map.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            payment.setPaymentStatus(PaymentStatus.REFUNDED);
            paymentRepository.save(payment);
            return (Map<String, Object>) response.getBody().get("response");
        }

        throw new RuntimeException("결제 취소 실패");
    }

    @Transactional
    public String getAccessToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode body = mapper.createObjectNode();
        body.put("imp_key", portOneConfig.getApiKey());
        body.put("imp_secret", portOneConfig.getApiSecret());

        HttpEntity<String> request = new HttpEntity<>(body.toString(), headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                "https://api.iamport.kr/users/getToken",
                HttpMethod.POST,
                request,
                Map.class
        );

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            Map<String, Object> responseBody = (Map<String, Object>) response.getBody().get("response");
            return (String) responseBody.get("access_token");
        }

        throw new RuntimeException("토큰 발급 실패");
    }


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
        counsel.setReservationStatus(ReservationStatus.RESERVATION_SCHEDULED);


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
