package com.example.aboutme.reservation;

import com.example.aboutme._core.error.exception.Exception400;
import com.example.aboutme._core.utils.DayOfWeekConverter;
import com.example.aboutme._core.utils.Formatter;
import com.example.aboutme.reservation.enums.ReservationStatus;
import com.example.aboutme.reservation.reservationRequest.ReservationTempRepDTO;
import com.example.aboutme.reservation.resrvationResponse.ReservationDetailsDTO;
import com.example.aboutme.schedule.Schedule;
import com.example.aboutme.schedule.ScheduleRepository;
import com.example.aboutme.user.SessionUser;
import com.example.aboutme.user.User;
import com.example.aboutme.user.UserRepository;
import com.example.aboutme.voucher.Voucher;
import com.example.aboutme.voucher.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final VoucherRepository voucherRepository;
    private final ScheduleRepository scheduleRepository;
    private final Formatter formatter;


    //    결제 전까지 예약 임시 저장
    @Transactional
    public Reservation createTempReservation(ReservationTempRepDTO reqDTO, SessionUser sessionUser) {
        User client = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception400("고객을 찾을 수 없습니다."));
        User expert = userRepository.findById(reqDTO.expertId())
                .orElseThrow(() -> new Exception400("전문가를 찾을 수 없습니다."));
        Voucher voucher = voucherRepository.findById(reqDTO.voucherId())
                .orElseThrow(() -> new Exception400("바우처를 찾을 수 없습니다."));

        //이넘으로 변경
        DayOfWeek dayOfWeekEnum = DayOfWeekConverter.toEnum(reqDTO.dayOfWeek());

        // 해당 스케줄 조회
        Schedule schedule = scheduleRepository.findByExpertIdAndDayOfWeekAndStartTime(
                        reqDTO.expertId(), dayOfWeekEnum, reqDTO.startTime())
                .orElseThrow(() -> new Exception400("해당 시간을 찾을 수 없습니다."));

        Reservation reservation = Reservation.builder()
                .client(client)
                .reservationDate(reqDTO.reservationDate())
                .dayOfWeek(reqDTO.dayOfWeek())
                .startTime(reqDTO.startTime())
                .expert(expert)
                .voucher(voucher)
                .schedule(schedule)
                .status(ReservationStatus.PENDING)
                .build();

        return reservationRepository.save(reservation);
    }


    //예약 페이지의 바우처와 유저정보 조회
    public ReservationDetailsDTO getReservationDetails(Integer voucherId, Integer expertId) {
        Voucher voucher = voucherRepository.findById(voucherId).orElseThrow(() -> new Exception400("해당하는 이용권을 찾을 수 없습니다."));
        User user = userRepository.findById(expertId).orElseThrow(() -> new Exception400("해당하는 전문가를 찾을 수 없습니다."));

        // 가격 포맷
        String formattedPrice = formatter.number((int) voucher.getPrice());

        // 엔티티를 DTO로 변환
        ReservationDetailsDTO.VoucherDTO voucherDTO = new ReservationDetailsDTO.VoucherDTO(
                voucher.getId(),
                voucher.getVoucherType().getKorean(),
                voucher.getExpert().getId(),
                formattedPrice,
                voucher.getCount(),
                voucher.getDuration()
        );
        ReservationDetailsDTO.UserDTO userDTO = new ReservationDetailsDTO.UserDTO(
                user.getId(),
                user.getLevel().getKorean()
        );

        return new ReservationDetailsDTO(voucherDTO, userDTO);
    }


    @Transactional
    public void Reservation(Reservation reservation) {
        // 인증처리

        // 조건처리

        // 리스트
        List<Reservation> reservations = reservationRepository.findAll();

    }


    public void 예약조회하기() {

    }
}


