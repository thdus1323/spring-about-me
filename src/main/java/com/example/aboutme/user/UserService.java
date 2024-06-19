package com.example.aboutme.user;

import com.example.aboutme.user.enums.UserRole;
import com.example.aboutme.user.record.expertFindRecord.ExpertInfoRecord;
import com.example.aboutme.user.record.expertFindRecord.FindWrapperRecord;
import com.example.aboutme.user.record.expertFindRecord.VoucherImageRecord;
import com.example.aboutme.voucher.Voucher;
import com.example.aboutme.voucher.VoucherRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
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


    // 상담가리스트 (record)
    public FindWrapperRecord getExpertFind(){

        // 1. 모든 유저 찾기
        List<User> users = userRepository.findAll();

        // 2. userRole이 EXPERT인 유저만 필터링
        List<User> expertUsers = users.stream()
                .filter(user -> user.getUserRole() == UserRole.EXPERT)
                .toList();

        // 3.ExpertinfoDTO 생성
        List<ExpertInfoRecord> expertInfos =  expertUsers.stream().map(user -> {

          //4. voucher 이미지 찾기
          List<Voucher> vouchersImages = voucherRepository.findByExpertId(user.getId());

          List<VoucherImageRecord> voucherImageDTOs = vouchersImages.stream().map(voucher -> {
              return new VoucherImageRecord(voucher.getImagePath());
          }).toList();

          return new ExpertInfoRecord(user.getId(),user.getName(),user.getExpertTitle(),user.getProfileImage(),voucherImageDTOs);
        }).toList();

        return new FindWrapperRecord(expertInfos);

    }
}
