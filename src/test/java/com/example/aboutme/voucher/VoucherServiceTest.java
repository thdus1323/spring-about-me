package com.example.aboutme.voucher;

import com.example.aboutme._core.utils.Formatter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
class VoucherServiceTest {
    @Autowired
    private VoucherRepository voucherRepository;
    private Formatter formatMapper;

    @Test
    void getVoucherListByExpertId() {
        int expertId = 21;
        double lowestPrice = voucherRepository.findLowestPriceByExpertId(expertId);
        System.out.println("lowestPrice = " + lowestPrice);
        String price = formatMapper.number((int) lowestPrice);
        System.out.println("price = " + price);
        List<Voucher> vouchers = voucherRepository.findByExpertId(expertId);
        vouchers.forEach(voucher -> System.out.println("voucher = " + voucher));
    }
}