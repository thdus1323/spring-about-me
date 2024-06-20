package com.example.aboutme.user;

import com.example.aboutme._core.utils.Formatter;
import com.example.aboutme.review.ReviewRepository;
import com.example.aboutme.user.UserResponseDTO.ExpertFindDetailDTO.*;
import com.example.aboutme.user.enums.SpecType;
import com.example.aboutme.user.enums.UserRole;
import com.example.aboutme.user.pr.PRRepository;
import com.example.aboutme.user.spec.SpecRepository;
import com.example.aboutme.user.record.expertFindRecord.ExpertInfoRecord;
import com.example.aboutme.user.record.expertFindRecord.FindWrapperRecord;
import com.example.aboutme.user.record.expertFindRecord.VoucherImageRecord;
import com.example.aboutme.voucher.Voucher;
import com.example.aboutme.voucher.VoucherRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final VoucherRepository voucherRepository;
    private final SpecRepository specRepository;
    private final PRRepository prRepository;
    private final Formatter formatter;
    private final UserNativeRepository userNativeRepository;


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
        return user;
    }

    public DetailDTORecord getExpertDetails(Integer expertId) {
        User user = userRepository.findById(expertId).orElseThrow(() -> new RuntimeException("User not found"));
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
}
