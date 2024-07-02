package com.example.aboutme.counsel;

import com.example.aboutme._core.error.exception.Exception400;
import com.example.aboutme._core.error.exception.Exception401;
import com.example.aboutme._core.error.exception.Exception403;
import com.example.aboutme._core.error.exception.Exception404;
import com.example.aboutme._core.utils.DayOfWeekConverter;
import com.example.aboutme._core.utils.Formatter;
import com.example.aboutme.counsel.CounselRequestRecord.ReservationRepDTO;
import com.example.aboutme.counsel.CounselResponseRecord.CounselDTO.CounselDTORecord;
import com.example.aboutme.counsel.CounselResponseRecord.CounselDTO.UserRecord;
import com.example.aboutme.counsel.CounselResponseRecord.MakeReservationDetailsDTO;
import com.example.aboutme.counsel.CounselResponseRecord.ReservationDetailsDTO;
import com.example.aboutme.counsel.enums.CounselStatus;
import com.example.aboutme.counsel.enums.ReservationStatus;
import com.example.aboutme.counsel.enums.ReviewState;
import com.example.aboutme.payment.Payment;
import com.example.aboutme.payment.PaymentRepository;
import com.example.aboutme.payment.PaymentResponseRecord.PaymentDetailsDTO;
import com.example.aboutme.schedule.Schedule;
import com.example.aboutme.schedule.ScheduleRepository;
import com.example.aboutme.user.SessionUser;
import com.example.aboutme.user.User;
import com.example.aboutme.user.UserRepository;
import com.example.aboutme.voucher.Voucher;
import com.example.aboutme.voucher.VoucherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;

import java.time.DayOfWeek;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CounselService {

    private final UserRepository userRepository;
    private final VoucherRepository voucherRepository;
    private final CounselRepository counselRepository;
    private final ScheduleRepository scheduleRepository;
    private final PaymentRepository paymentRepository;

    //결재완료전 결재대기생성
    public PaymentDetailsDTO getTempReservation(Integer reservationId) {
        log.info("예약조회하기 called with reservationId: {}", reservationId);

        Counsel reservation = counselRepository.findById(reservationId)
                .orElseThrow(() -> new Exception400("해당 예약정보를 찾을 수 없습니다."));

        PaymentDetailsDTO.ReservationDTO reservationDTO = PaymentDetailsDTO.ReservationDTO.builder()
                .reservationDate(reservation.getCounselDate())
                .reservationTime(reservation.getCounselTime())
                .reservationId(reservation.getId())
                .build();

        log.info("Reservation found: {}", reservation);

        User expert = userRepository.findById(reservation.getExpert().getId())
                .orElseThrow(() -> new Exception400("전문가를 찾을 수 없습니다."));
        log.info("Expert found: {}", expert);

        Voucher voucher = voucherRepository.findById(reservation.getVoucher().getId())
                .orElseThrow(() -> new Exception400("바우처를 찾을 수 없습니다."));
        log.info("Voucher found: {}", voucher);

        String formattedPrice = Formatter.number((int) voucher.getPrice());
        log.info("Formatted price: {}", formattedPrice);


        PaymentDetailsDTO.VoucherDTO voucherDTO = new PaymentDetailsDTO.VoucherDTO(
                voucher.getId(),
                voucher.getVoucherType().getKorean(),
                voucher.getExpert().getId(),
                formattedPrice,
                voucher.getCount(),
                voucher.getDuration()
        );
        PaymentDetailsDTO.ExpertDTO expertDTO = new PaymentDetailsDTO.ExpertDTO(
                expert.getId(),
                expert.getLevel().getKorean()
        );

        PaymentDetailsDTO paymentDetailsDTO = new PaymentDetailsDTO(voucherDTO, expertDTO, reservationDTO);
        log.info("PaymentDetailsDTO: {}", paymentDetailsDTO);

        return paymentDetailsDTO;
    }

    //마이페이지에서 예약생성
    @Transactional
    public void makeReservation(ReservationRepDTO reqDTO, SessionUser sessionUser) {

        User client = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception400("고객을 찾을 수 없습니다."));
        User expert = userRepository.findById(reqDTO.expertId())
                .orElseThrow(() -> new Exception400("전문가를 찾을 수 없습니다."));
        Voucher voucher = voucherRepository.findById(reqDTO.voucherId())
                .orElseThrow(() -> new Exception400("바우처를 찾을 수 없습니다."));
        Payment payment = paymentRepository.findById(reqDTO.paymentId())
                .orElseThrow(() -> new Exception400("바우처를 찾을 수 없습니다."));
        //이넘으로 변경
        DayOfWeek dayOfWeekEnum = DayOfWeekConverter.toEnum(reqDTO.dayOfWeek());

        // 해당 스케줄 조회
        Schedule schedule = scheduleRepository.findByExpertIdAndDayOfWeekAndStartTime(
                        reqDTO.expertId(), dayOfWeekEnum, reqDTO.startTime())
                .orElseThrow(() -> new Exception400("해당 시간을 찾을 수 없습니다."));

        Counsel makeReservation = Counsel.builder()
                .client(client)
                .expert(expert)
                .voucher(voucher)
                .schedule(schedule)
                .payment(payment)
                .reservationStatus(ReservationStatus.RESERVATION_SCHEDULED)
                .counselStatus(CounselStatus.COUNSEL_PENDING)
                .dayOfWeek(reqDTO.dayOfWeek())
                .counselDate(reqDTO.reservationDate())
                .counselTime(reqDTO.startTime())
                .reviewState(ReviewState.REVIEW_PENDING)
                .build();

        counselRepository.save(makeReservation);
    }

    //    결제 전까지 예약 임시 저장
    @Transactional
    public Counsel createTempReservation(ReservationRepDTO reqDTO, SessionUser sessionUser) {

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

        Counsel reservation = Counsel.builder()
                .client(client)
                .counselDate(reqDTO.reservationDate())
                .dayOfWeek(reqDTO.dayOfWeek())
                .counselTime(reqDTO.startTime())
                .expert(expert)
                .voucher(voucher)
                .schedule(schedule)
                .reservationStatus(ReservationStatus.RESERVATION_PENDING)
                .counselStatus(CounselStatus.COUNSEL_PENDING)
                .build();

        return counselRepository.save(reservation);
    }


    //예약 페이지의 바우처와 유저정보 조회
    public ReservationDetailsDTO getReservationDetails(Integer voucherId, Integer expertId) {

        Voucher voucher = voucherRepository.findById(voucherId).orElseThrow(() -> new Exception400("해당하는 이용권을 찾을 수 없습니다."));
        User user = userRepository.findById(expertId).orElseThrow(() -> new Exception400("해당하는 전문가를 찾을 수 없습니다."));

        // 가격 포맷
        String formattedPrice = Formatter.number((int) voucher.getPrice());

        // 엔티티를 DTO로 변환
        ReservationDetailsDTO.VoucherDTO voucherDTO = ReservationDetailsDTO.VoucherDTO.builder()
                .id(voucher.getId()).
                voucherType(voucher.getVoucherType().getKorean())
                .price(formattedPrice)
                .expertId(voucher.getExpert().getId())
                .count(voucher.getCount())
                .duration(voucher.getDuration())
                .build();

        ReservationDetailsDTO.UserDTO userDTO = new ReservationDetailsDTO.UserDTO(
                user.getId(),
                user.getLevel().getKorean()
        );

        return new ReservationDetailsDTO(voucherDTO, userDTO);
    }

    //예약 페이지의 바우처와 유저정보 조회
    public MakeReservationDetailsDTO getMakeReservationDetails(Integer voucherId, Integer expertId, Integer paymentId) {

        Voucher voucher = voucherRepository.findById(voucherId).orElseThrow(() -> new Exception400("해당하는 이용권을 찾을 수 없습니다."));
        User user = userRepository.findById(expertId).orElseThrow(() -> new Exception400("해당하는 전문가를 찾을 수 없습니다."));
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new Exception400("해당하는 이용권 결제내역을 찾을 수 없습니다."));
        System.out.println("payment = " + payment);
        // 가격 포맷
        String formattedPrice = Formatter.number((int) voucher.getPrice());

        // 엔티티를 DTO로 변환
        MakeReservationDetailsDTO.VoucherDTO voucherDTO = MakeReservationDetailsDTO.VoucherDTO.builder()
                .id(voucher.getId()).
                voucherType(voucher.getVoucherType().getKorean())
                .price(formattedPrice)
                .paymentId(payment.getId())
                .expertId(payment.getExpert().getId())
                .count(payment.getVoucherCount())
                .duration(payment.getVoucherDuration())
                .build();

        MakeReservationDetailsDTO.UserDTO userDTO = new MakeReservationDetailsDTO.UserDTO(
                user.getId(),
                user.getLevel().getKorean()
        );

        return new MakeReservationDetailsDTO(voucherDTO, userDTO);
    }

    @Transactional
    public void Reservation(Counsel reservation) {
        // 인증처리

        // 조건처리

        // 리스트
        List<Counsel> reservations = counselRepository.findAll();

    }

    //상담일정
    @Transactional
    public CounselDTORecord findCounsel(SessionUser sessionUser) {

        // 0. 인증
        if (sessionUser == null) {
            throw new Exception403("인증되지 않은 유저입니다");
        }
        User expert = userRepository.findById(sessionUser.getId()).orElseThrow(() -> new Exception404("전문가를 찾을 수 없습니다."));

        // 1. 상담사 리스트 찾기
        List<Counsel> counselList = counselRepository.findAllCounselByExpertId(sessionUser.getId());

        // 2. Transform Counsel list to UserRecord list
        List<UserRecord> userRecords = counselList.stream().map(counsel -> {

            // 3. 유저정보찾기
            User user = userRepository.findById(counsel.getClient().getId())
                    .orElseThrow(() -> new Exception404("해당 유저를 찾지 못했습니다"));

            // 4. Voucher 전체카운트 찾기
            Integer voucherTotal = counselRepository.countAllByClientId(user.getId());

            // 5. Vocher 남은 카운트찾기
            Integer voucherRemain = counselRepository.countByClientIdAndState(user.getId(), CounselStatus.COUNSEL_PENDING);

            // 6. VoucherType 변환
            String voucherType = counsel.getVoucher().getVoucherType().getKorean();

            boolean isCompleted = counsel.getReservationStatus() == ReservationStatus.RESERVATION_SCHEDULED;

            // UserRecord 생성
            return new UserRecord(
                    user.getId(),
                    user.getName(),
                    user.getProfileImage(),
                    counsel.getReservationStatus().getKorean(),
                    counsel.getId(),
                    voucherType,
                    voucherTotal,
                    voucherRemain,
                    counsel.getCounselDate(), // applyDate는 Counsel의 counselDate를 사용
                    isCompleted
            );

        }).collect(Collectors.toList());

        // 최종적으로 CounselDTORecord를 반환
        return new CounselDTORecord(expert.getId(), userRecords);
    }


    //상담완료
    @Transactional
    public void CounselComplete(SessionUser sessionUser, Integer counselId) {
        // 0. 인증처리
        if (sessionUser == null) {
            throw new Exception404("인증이 필요한 서비스입니다.");
        }
        // 1. 권한처리
        Counsel counsel = counselRepository.findById(counselId).orElseThrow(() -> new Exception403("해당 상담을 처리할 권한이 없습니다."));

        // 2. counsel 수정
        counsel.completeCounsel();

        // 3. reservation 수정

    }

    // 업데이트 체크용
    @Transactional
    public void updateCounselStatus(Integer counselId, ReservationStatus status) {
        Counsel counsel = counselRepository.findById(counselId)
                .orElseThrow(() -> new RuntimeException("Counsel not found"));
        counsel.setReservationStatus(status);
        counselRepository.save(counsel);
    }


    @Transactional
    public Page<CounselDTORecord> pagingCounsel(SessionUser sessionUser, int page, int size) {

        // 0. 인증
        if (sessionUser == null) {
            throw new Exception403("인증되지 않은 유저입니다");
        }
        User expert = userRepository.findById(sessionUser.getId()).orElseThrow(() -> new Exception404("전문가를 찾을 수 없습니다."));

        // 1. 상담사 리스트 찾기
        List<Counsel> counselList = counselRepository.findAllCounselByExpertId(sessionUser.getId());

        // 2. Transform Counsel list to UserRecord list
        List<UserRecord> userRecords = counselList.stream().map(counsel -> {

            // 3. 유저정보찾기
            User user = userRepository.findById(counsel.getClient().getId())
                    .orElseThrow(() -> new Exception404("해당 유저를 찾지 못했습니다"));

            // 4. Voucher 전체카운트 찾기
            Integer voucherTotal = counselRepository.countAllByClientId(user.getId());

            // 5. Vocher 남은 카운트찾기
            Integer voucherRemain = counselRepository.countByClientIdAndState(user.getId(), CounselStatus.COUNSEL_PENDING);

            // 6. VoucherType 변환
            String voucherType = counsel.getVoucher().getVoucherType().getKorean();

            boolean isCompleted = counsel.getReservationStatus() == ReservationStatus.RESERVATION_SCHEDULED;


            // UserRecord 생성
            return new UserRecord(
                    user.getId(),
                    user.getName(),
                    user.getProfileImage(),
                    counsel.getReservationStatus().getKorean(),
                    counsel.getId(),
                    voucherType,
                    voucherTotal,
                    voucherRemain,
                    counsel.getCounselDate(), // applyDate는 Counsel의 counselDate를 사용
                    isCompleted
            );

        }).collect(Collectors.toList());

        // 페이징 처리
        int start = Math.min((int) PageRequest.of(page, size).getOffset(), userRecords.size());
        int end = Math.min((start + PageRequest.of(page, size).getPageSize()), userRecords.size());
        Page<UserRecord> userRecordPage = new PageImpl<>(userRecords.subList(start, end), PageRequest.of(page, size), userRecords.size());

        // 최종적으로 CounselDTORecord를 반환
        CounselDTORecord counselDTORecord = new CounselDTORecord(expert.getId(),userRecordPage.getContent());

        return new PageImpl<>(Collections.singletonList(counselDTORecord), PageRequest.of(page, size), userRecords.size());
    }

    @Transactional
    public void reservationUpdate(Integer counselId, SessionUser sessionUser) {
        // 0. 인증처리
        if (sessionUser == null){
            throw new Exception403("인증되지 않은 유저입니다.");
        }
        Counsel counsel = counselRepository.findById(counselId).orElseThrow(() -> new Exception404("해당 상담내역을 찾을 수 없습니다."));

        // 1. 권한처리
        if (sessionUser.getId() != counsel.getExpert().getId()){
            throw  new Exception401("해당 상담내역을 수정할 권한이 없습니다.");
        }

        counsel.completeReservation();
    }
}


