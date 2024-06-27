package com.example.aboutme.counsel;

import com.example.aboutme._core.error.exception.Exception403;
import com.example.aboutme._core.error.exception.Exception404;
import com.example.aboutme.counsel.CounselResponseRecord.CounselDTO.CounselDTORecord;
import com.example.aboutme.counsel.CounselResponseRecord.CounselDTO.UserRecord;
import com.example.aboutme.counsel.enums.CounselStateEnum;
import com.example.aboutme.user.SessionUser;
import com.example.aboutme.user.User;
import com.example.aboutme.user.UserRepository;
import com.example.aboutme.voucher.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CounselService {
    private final CounselRepository counselRepository;
    private final UserRepository userRepository;
    private final VoucherRepository voucherRepository;


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
            Integer voucherRemain = counselRepository.countByClientIdAndState(user.getId(), CounselStateEnum.PENDING);

            // 6. VoucherType 변환
            String voucherType = counsel.getVoucher().getVoucherType().getKorean();

            // UserRecord 생성
            return new UserRecord(
                    user.getId(),
                    user.getName(),
                    user.getProfileImage(),
                    voucherType,
                    voucherTotal,
                    voucherRemain,
                    counsel.getCounselDate() // applyDate는 Counsel의 counselDate를 사용
            );

        }).collect(Collectors.toList());

        // 최종적으로 CounselDTORecord를 반환
        return new CounselDTORecord(expert.getId(), expert.getProfileImage(), userRecords);
    }

}


