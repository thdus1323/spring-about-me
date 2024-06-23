package com.example.aboutme.reservation;

import com.example.aboutme._core.utils.Formatter;
import com.example.aboutme.schedule.ScheduleRepository;
import com.example.aboutme.voucher.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final VoucherRepository voucherRepository;
    private final ScheduleRepository scheduleRepository;
    private final Formatter formatter;


    //예약 페이지의 예약 가능 시간대 조회
//    public ReservationDetailsDTO getReservationDetails(Integer voucherId, Integer expertId) {
//        Voucher voucher = voucherRepository.findById(voucherId).orElseThrow(() -> new Exception400("해당하는 이용권을 찾을 수 없습니다."));
//        List<Schedule> schedules = scheduleRepository.findByExpertId(expertId);
//// 널 처리
//        schedules = schedules.stream().map(NullHandlerUtil::handleNulls).toList();
//
//        List<Reservation> reservations = reservationRepository.findByExpertId(expertId);
//        // 가격 포맷
//        String formattedPrice = formatter.number((int) voucher.getPrice());
//        // 엔티티를 DTO로 변환
//        ReservationDetailsDTO.VoucherDTO voucherDTO = new ReservationDetailsDTO.VoucherDTO(
//                voucher.getId(),
//                voucher.getVoucherType().getKorean(),
//                voucher.getExpert().getId(),
//                formattedPrice,
//                voucher.getCount(),
//                voucher.getDuration(),
//                voucher.getImagePath(),
//                voucher.getStartDate(),
//                voucher.getCreatedAt().toLocalDateTime(),
//                voucher.getUpdatedAt().toLocalDateTime()
//        );
//
//        List<ReservationDetailsDTO.ScheduleDTO> scheduleDTOs = schedules.stream()
//                .map(schedule -> new ReservationDetailsDTO.ScheduleDTO(
//                        schedule.getId(),
//                        schedule.getExpert().getId(),
//                        schedule.getStartTime(),
//                        schedule.getEndTime(),
//                        schedule.getRestType().getKorean(),
//                        schedule.getStartDay().getKorean(),
//                        schedule.getEndDay().getKorean(),
//                        schedule.getSpecificDate(),
//                        schedule.getLunchStartTime(),
//                        schedule.getLunchEndTime()
//                ))
//                .collect(toList());
//
//        List<ReservationDetailsDTO.ReservationDTO> reservationDTOs = reservations.stream()
//                .map(reservation -> new ReservationDetailsDTO.ReservationDTO(
//                        reservation.getId(),
//                        reservation.getExpert().getId(),
//                        reservation.getClient().getId(),
//                        reservation.getVoucher().getId(),
//                        reservation.getStatus().getKorean(),
//                        reservation.getStartTime(),
//                        reservation.getReservationDate(),
//                        reservation.getSchedule().getId()
//                ))
//                .collect(toList());
//
//        // 예약 가능한 시간 계산
//        List<String> availableTimes = calculateAvailableTimes(schedules, reservations);
//        System.out.println("availableTimes = " + availableTimes);
//
//        return new ReservationDetailsDTO(voucherDTO, availableTimes, scheduleDTOs, reservationDTOs);
//    }


//    private List<String> calculateAvailableTimes(List<Schedule> schedules, List<Reservation> reservations) {
//        List<String> availableTimes = new ArrayList<>();
//
//        // 예약된 시간대를 가져옵니다.
//        List<LocalTime> reservedTimes = reservations.stream()
//                .map(Reservation::getStartTime)
//                .toList();
//
//        // 스케줄에 따른 예약 가능한 시간을 계산합니다.
//        for (Schedule schedule : schedules) {
//            // 점심 시간대를 제외한 시간을 계산합니다.
//            List<LocalTime> times = IntStream.rangeClosed(schedule.getStartTime().getHour(), schedule.getEndTime().getHour() - 1)
//                    .mapToObj(hour -> LocalTime.of(hour, 0))
//                    .collect(Collectors.toList());
//
//            // 점심 시간대를 제외합니다.
//            times.removeIf(time -> !time.isBefore(schedule.getLunchStartTime()) && !time.isAfter(schedule.getLunchEndTime()));
//
//            // 이미 예약된 시간을 제외합니다.
//            times.removeAll(reservedTimes);
//
//            // 포맷하여 추가합니다.
//            availableTimes.addAll(times.stream().map(LocalTime::toString).collect(Collectors.toList()));
//        }
//
//        return availableTimes;
//    }

    @Transactional
    public void Reservation(Reservation reservation) {
        // 인증처리

        // 조건처리

        // 리스트
        List<Reservation> reservations = reservationRepository.findAll();

    }


}


