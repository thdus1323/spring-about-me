package com.example.aboutme.user;

import com.example.aboutme._core.error.exception.Exception401;
import com.example.aboutme._core.error.exception.Exception403;
import com.example.aboutme._core.utils.Formatter;
import com.example.aboutme.comm.CommRepository;
import com.example.aboutme.review.ReviewRepository;
import com.example.aboutme.user.UserResponseDTO.ExpertFindDetailDTO.*;
import com.example.aboutme.user.enums.SpecType;
import com.example.aboutme.user.enums.UserRole;
import com.example.aboutme.user.pr.PRRepository;
import com.example.aboutme.user.spec.SpecRepository;
import com.example.aboutme.user.UserResponseDTO.expertFindDTO.ExpertInfoRecord;
import com.example.aboutme.user.UserResponseDTO.expertFindDTO.FindWrapperRecord;
import com.example.aboutme.user.UserResponseDTO.expertFindDTO.VoucherImageRecord;
import com.example.aboutme.voucher.Voucher;
import com.example.aboutme.voucher.VoucherRepository;
import com.example.aboutme.voucher.enums.VoucherType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    private final ReviewRepository reviewRepository;
    private final VoucherRepository voucherRepository;
    private final SpecRepository specRepository;
    private final PRRepository prRepository;
    private final Formatter formatter;

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

    public DetailDTORecord getFindExpertDetails(Integer expertId) {
        User user = userRepository.findById(expertId).orElseThrow(() -> new Exception403("유저정보를 찾을 수 없습니다."));
        UserRecord userRecord = new UserRecord(user.getId(), user.getName(), user.getProfileImage());

        double price = voucherRepository.findLowestPriceByExpertId(expertId);
        String lowestPrice = formatter.number((int) price); // 포맷터에서 가격을 포맷팅

        List<ReviewRecord> reviewRecords = reviewRepository.findByExpertId(expertId).stream()
                .map(review -> new ReviewRecord(review.getId(), review.getContent()))
                .collect(Collectors.toList());

        List<PRRecord> prRecords = prRepository.findByExpertId(expertId).stream()
                .map(pr -> new PRRecord(pr.getUser().getId(), pr.getIntro(), pr.getEffects(), pr.getMethods()))
                .collect(Collectors.toList());

        // 학력과 경력을 각각 나눔
        List<SpecRecord> careerRecords = specRepository.findByExpertId(expertId).stream()
                .filter(spec -> spec.getSpecType() == SpecType.CAREER)
                .map(spec -> new SpecRecord(spec.getUser().getId(), spec.getSpecType(), spec.getDetails()))
                .collect(Collectors.toList());

        List<SpecRecord> educationRecords = specRepository.findByExpertId(expertId).stream()
                .filter(spec -> spec.getSpecType() == SpecType.EDUCATION)
                .map(spec -> new SpecRecord(spec.getUser().getId(), spec.getSpecType(), spec.getDetails()))
                .collect(Collectors.toList());

        return new DetailDTORecord(userRecord, lowestPrice, reviewRecords, prRecords, careerRecords,educationRecords);
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
    // 클라이언트 메인
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
