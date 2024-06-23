package com.example.aboutme.payment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    //결제정보 검색
    Optional<Payment> findByMerchantUid(String merchantUid);
}
