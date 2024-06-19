package com.example.aboutme.voucher;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class VoucherRepositoryTest {
    @Autowired
    VoucherRepository voucherRepository;


    @Test
    public void findByCustomerId(){

        //given
        Integer expertId = 21;

        // when
        List<Voucher> voucherList = voucherRepository.findByExpertId(expertId);

        // eye
        for (Voucher voucher : voucherList) {
            System.out.println(voucher.getImagePath());
        }
    }
}
