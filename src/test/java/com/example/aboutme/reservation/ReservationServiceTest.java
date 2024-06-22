package com.example.aboutme.reservation;

import com.example.aboutme._core.utils.Formatter;
import com.example.aboutme.schedule.ScheduleRepository;
import com.example.aboutme.voucher.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ReservationServiceTest {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private VoucherRepository voucherRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

    //테스트에서는 빈주입이 안되어서 직접 뉴~
    private Formatter formatter = new Formatter();


}