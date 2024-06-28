package com.example.aboutme.reservation;

import com.example.aboutme.counsel.Counsel;
import com.example.aboutme.counsel.CounselRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class counselRepositoryTest {

    @Autowired
    CounselRepository counselRepository;


    @Test
    public void findAll_test() {
        //when
        List<Counsel> reservations = counselRepository.findAll();
        // eye
        System.out.println("결과값 =-=-=-=-=-=-=-=-=-=-" + reservations);
    }


}
