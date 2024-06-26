package com.example.aboutme.payment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PaymentRepositoryTest {
    @Autowired
    private PaymentRepository paymentRepository;
    @Test
    void findByClientId() {
        int id = 1;
        List<Payment> p = paymentRepository.findByClientId(id);
        p.stream().forEach(payment -> System.out.println("payment = " + payment.getVoucher().getId()));
    }
}