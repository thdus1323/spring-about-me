package com.example.aboutme.user;

import com.example.aboutme.comm.CommRepository;
import com.example.aboutme.comm.CommResponse;
import com.example.aboutme.voucher.enums.VoucherType;
import com.example.aboutme.user.enums.UserRole;
import com.example.aboutme.voucher.Voucher;
import com.example.aboutme.voucher.VoucherRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final CommRepository commRepository;
    private final UserNativeRepository userNativeRepository;
    private final VoucherRepository voucherRepository;

//회원가입
//    @Transactional
//    public void joinByEmail(UserRequest.JoinDTO reqDTO){
//        userNativeRepository.join(reqDTO);
//    }

    //    //로그인
//    public User loginByName(UserRequest.LoginDTO reqDTO) {
//        User sessionUser = userNativeRepository.login(reqDTO);
//        return sessionUser;
//    }
    @Transactional
    public User loginByName(UserRequest.LoginDTO reqDTO) {
        User user = userNativeRepository.login(reqDTO);
        user.getSpecs().size();
        return user;
    }


    // 전문가(상담사 리스트)
    public List<UserResponse.ExpertUserDTO> getAllExpertUsers() {

        // 1. 모든 유저 찾기
        List<User> users = userRepository.findAll();

        // 2. userRole이 EXPERT인 유저만 필터링
        List<User> expertUsers = users.stream()
                .filter(user -> user.getUserRole() == UserRole.EXPERT)
                .collect(Collectors.toList());


        // 3. ExpertUserDTO 리스트 생성
        List<UserResponse.ExpertUserDTO> result = expertUsers.stream().map(user -> {

            List<Voucher> voucherList = voucherRepository.findByExpertId(user.getId());

            List<UserResponse.ExpertUserDTO.VoucherImageDTO> voucherImages = voucherList.stream()
                    .map(voucher -> new UserResponse.ExpertUserDTO.VoucherImageDTO(voucher.getImagePath()))
                    .collect(Collectors.toList());

            return new UserResponse.ExpertUserDTO(user, voucherImages);
        }).collect(Collectors.toList());

        return result;
    }

    public HashMap<String, Object> getClientMain() {
        List<UserResponse.ClientMainDTO.CommDTO> comms = commRepository.findCommsWithReply();
        List<UserResponse.ClientMainDTO.ExpertDTO> experts = userRepository.findExpert();
        List<UserResponse.ClientMainDTO.VoucherDTO> vouchers = voucherRepository.findAllVouchers();

        List<Map<String, Object>> expertWithVouchers = experts.stream().map(expert -> {
            Map<String, Object> expertMap = new HashMap<>();
            expertMap.put("expert", expert);

            List<String> voucherTypes = vouchers.stream()
                    .filter(voucher -> voucher.getExpertId().equals(expert.getExpertId()))
                    .map(UserResponse.ClientMainDTO.VoucherDTO::getVoucherType)
                    .map(VoucherType::name)
                    .collect(Collectors.toList());

            // 특정 바우처 타입을 가진지 여부를 판단하는 boolean 값 추가
            expertMap.put("hasTextTherapy", voucherTypes.contains("TEXT_THERAPY"));
            expertMap.put("hasVoiceTherapy", voucherTypes.contains("VOICE_THERAPY"));
            expertMap.put("hasVideoTherapy", voucherTypes.contains("VIDEO_THERAPY"));

            expertMap.put("voucherTypes", voucherTypes);
            return expertMap;
        }).toList();

        HashMap<String, Object> clientMain = new HashMap<>();
        clientMain.put("comms", comms);
        clientMain.put("experts", expertWithVouchers);
        // clientMain.put("vouchers", vouchers);
        return clientMain;
    }
}