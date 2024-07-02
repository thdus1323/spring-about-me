package com.example.aboutme.payment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Payment findByImpUid(String impUid);

    //결제정보 검색
    Optional<Payment> findByMerchantUid(String merchantUid);

    //클라이언트의 결제내역
    List<Payment> findByClientId(Integer clientId);
}
