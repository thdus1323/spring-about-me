package com.example.aboutme.voucher;

import com.example.aboutme._core.error.exception.Exception403;
import com.example.aboutme._core.utils.Formatter;
import com.example.aboutme.user.User;
import com.example.aboutme.user.UserRepository;
import com.example.aboutme.voucher.VoucherResponseDTO.voucherList.VoucherListRecord;
import com.example.aboutme.voucher.VoucherResponseDTO.voucherList.VoucherRecord;
import com.example.aboutme.voucher.VoucherResponseDTO.voucherList.VoucherUserRecord;
import com.example.aboutme.voucher.enums.VoucherType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class VoucherService {

    private final VoucherRepository voucherRepository;
    private final UserRepository userRepository;

    //바우처 목록과 최저 가격
    public VoucherListRecord getVoucherListByExpertId(Integer expertId) {
        User user = userRepository.findById(expertId).orElseThrow(() -> new Exception403("유저정보를 찾을 수 없습니다."));
        VoucherUserRecord voucherUserRecord = new VoucherUserRecord(user.getId(), user.getName(), user.getLevel().getKorean(), user.getProfileImage());

        // price가 0일 때 0원을 반환하도록 로직 수정
        Optional<Double> optionalPrice = voucherRepository.findLowestPriceByExpertId(expertId);
        double price = optionalPrice.orElse(0.0);
        String lowestPrice = Formatter.number((int) price); // 포맷터에서 가격을 포맷팅
        log.debug("최저 가격: {}", lowestPrice);

        List<VoucherRecord> vouchers = voucherRepository.findByExpertId(expertId)
                .stream()
                .map(v -> new VoucherRecord(
                        v.getVoucherType(),
                        v.getId(),
                        Formatter.number((int) v.getPrice()),
                        v.getCount(),
                        v.getDuration(),
                        v.getImagePath()
                ))
                .collect(Collectors.toList());
        System.out.println("vouchers = " + vouchers);

        List<VoucherRecord> textVoucher = vouchers.stream()
                .filter(v -> v.voucherType() == VoucherType.TEXT_THERAPY)
                .collect(Collectors.toList());
        System.out.println("textVoucher = " + textVoucher);

        List<VoucherRecord> voiceVoucher = vouchers.stream()
                .filter(v -> v.voucherType() == VoucherType.VOICE_THERAPY)
                .collect(Collectors.toList());
        System.out.println("voiceVoucher = " + voiceVoucher);

        List<VoucherRecord> videoVoucher = vouchers.stream()
                .filter(v -> v.voucherType() == VoucherType.VIDEO_THERAPY)
                .collect(Collectors.toList());
        System.out.println("videoVoucher = " + videoVoucher);

        return new VoucherListRecord(lowestPrice, voucherUserRecord, textVoucher, voiceVoucher, videoVoucher);
    }


}
