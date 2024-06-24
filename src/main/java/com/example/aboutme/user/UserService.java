package com.example.aboutme.user;

import com.example.aboutme._core.error.exception.Exception403;
import com.example.aboutme._core.utils.Formatter;
import com.example.aboutme._core.utils.UserDefault;
import com.example.aboutme.comm.CommRepository;
import com.example.aboutme.counsel.Counsel;
import com.example.aboutme.counsel.CounselRepository;
import com.example.aboutme.review.ReviewRepository;
import com.example.aboutme.user.UserResponseDTO.ClientMainDTO.ClientMainDTORecord;
import com.example.aboutme.user.UserResponseDTO.ClientMainDTO.CommDTORecord;
import com.example.aboutme.user.UserResponseDTO.ClientMainDTO.ExpertDTORecord;
import com.example.aboutme.user.UserResponseDTO.ClientMainDTO.VoucherDTORecord;
import com.example.aboutme.user.UserResponseDTO.ExpertFindDetailDTO.*;
import com.example.aboutme.user.UserResponseDTO.ExpertMainDTO.CounselScheduleRecord;
import com.example.aboutme.user.UserResponseDTO.ExpertMainDTO.ExpertMainDTORecord;
import com.example.aboutme.user.UserResponseDTO.ExpertMainDTO.RecentReviewRecord;
import com.example.aboutme.user.UserResponseDTO.expertFindDTO.ExpertInfoRecord;
import com.example.aboutme.user.UserResponseDTO.expertFindDTO.FindWrapperRecord;
import com.example.aboutme.user.UserResponseDTO.expertFindDTO.VoucherImageRecord;
import com.example.aboutme.user.enums.OauthProvider;
import com.example.aboutme.user.enums.SpecType;
import com.example.aboutme.user.enums.UserRole;
import com.example.aboutme.user.oauth.UserResponse;
import com.example.aboutme.user.pr.PRRepository;
import com.example.aboutme.user.spec.SpecRepository;
import com.example.aboutme.voucher.Voucher;
import com.example.aboutme.voucher.VoucherRepository;
import com.example.aboutme.voucher.enums.VoucherType;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
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
    private final CounselRepository counselRepository;
    private final Formatter formatter;
    private final RedisTemplate<String, Object> redisTemplate;
//    @Transactional
//    public User loginByName(UserRequest.LoginDTO reqDTO) {
//        User user = userNativeRepository.login(reqDTO);
//        return user;
//    }




    public SessionUser loginByName(UserRequest.LoginDTO reqDTO) {
        User user = userRepository.findByEmailAndPassword(reqDTO.getEmail(), reqDTO.getPassword());
        return new SessionUser(user);
    }


    public DetailDTORecord getFindExpertDetails(Integer expertId) {
        User user = userRepository.findById(expertId).orElseThrow(() -> new Exception403("유저정보를 찾을 수 없습니다."));
        UserRecord userRecord = new UserRecord(user.getId(), user.getName(), user.getProfileImage());

        double price = voucherRepository.findLowestPriceByExpertId(expertId);
        String lowestPrice = formatter.number((int) price); // 포맷터에서 가격을 포맷팅

        List<ReviewRecord> reviewRecords = reviewRepository.findByExpertId(expertId).stream().map(review -> new ReviewRecord(review.getId(), review.getContent())).collect(Collectors.toList());

        List<PRRecord> prRecords = prRepository.findByExpertId(expertId).stream().map(pr -> new PRRecord(pr.getUser().getId(), pr.getIntro(), pr.getEffects(), pr.getMethods())).collect(Collectors.toList());

        // 학력과 경력을 각각 나눔
        List<SpecRecord> careerRecords = specRepository.findByExpertId(expertId).stream().filter(spec -> spec.getSpecType() == SpecType.CAREER).map(spec -> new SpecRecord(spec.getUser().getId(), spec.getSpecType(), spec.getDetails())).collect(Collectors.toList());

        List<SpecRecord> educationRecords = specRepository.findByExpertId(expertId).stream().filter(spec -> spec.getSpecType() == SpecType.EDUCATION).map(spec -> new SpecRecord(spec.getUser().getId(), spec.getSpecType(), spec.getDetails())).collect(Collectors.toList());

        return new DetailDTORecord(userRecord, lowestPrice, reviewRecords, prRecords, careerRecords, educationRecords);
    }


    // 상담가리스트 (record)
    public FindWrapperRecord getExpertFind() {

        // 1. 모든 유저 찾기
        List<User> users = userRepository.findAll();

        // 2. userRole이 EXPERT인 유저만 필터링
        List<User> expertUsers = users.stream().filter(user -> user.getUserRole() == UserRole.EXPERT).toList();

        // 3.ExpertinfoDTO 생성
        List<ExpertInfoRecord> expertInfos = expertUsers.stream().map(user -> {

            //4. voucher 이미지 찾기
            List<Voucher> vouchersImages = voucherRepository.findByExpertId(user.getId());

            List<VoucherImageRecord> voucherImageDTOs = vouchersImages.stream().map(voucher -> {
                return new VoucherImageRecord(voucher.getImagePath());
            }).toList();

            return new ExpertInfoRecord(user.getId(), user.getName(), user.getExpertTitle(), user.getProfileImage(), voucherImageDTOs);
        }).toList();

        return new FindWrapperRecord(expertInfos);

    }

    // 상담가 검색
    public FindWrapperRecord getExpertFindBySearch(LocalDateTime localDateTime) {

        // 1. 모든 유저 찾기
        List<User> users = userRepository.findAll();

        // 2. userRole이 EXPERT인 유저만 필터링
        List<User> expertUsers = users.stream().filter(user -> user.getUserRole() == UserRole.EXPERT).filter(user -> {
            List<Counsel> counsels = counselRepository.findCounselsByDateAndTime(localDateTime);
            return counsels.stream().noneMatch(counsel -> counsel.getClient().getId().equals(user.getId()));
        }).toList();

        // 3.ExpertinfoDTO 생성
        List<ExpertInfoRecord> expertInfos = expertUsers.stream().map(user -> {

            //4. voucher 이미지 찾기
            List<Voucher> vouchersImages = voucherRepository.findByExpertId(user.getId());

            List<VoucherImageRecord> voucherImageDTOs = vouchersImages.stream().map(voucher -> {
                return new VoucherImageRecord(voucher.getImagePath());
            }).toList();

            return new ExpertInfoRecord(user.getId(), user.getName(), user.getExpertTitle(), user.getProfileImage(), voucherImageDTOs);
        }).toList();

        return new FindWrapperRecord(expertInfos);

    }


    // 클라이언트 메인
    public ClientMainDTORecord getClientMain() {
        List<CommDTORecord> comms = commRepository.findCommsWithReply().stream().map(comm -> new CommDTORecord(comm.getCommunityId(), comm.getWriterName(), comm.getWriterImage(), comm.getExpertName(), comm.getExpertImage(), comm.getTitle(), comm.getContent(), comm.getCategory())).toList();

        List<ExpertDTORecord> experts = userRepository.findExpert().stream().map(expert -> {
            List<VoucherDTORecord> vouchers = voucherRepository.findByExpertId(expert.getExpertId()).stream().map(voucher -> new VoucherDTORecord(voucher.getVoucherType(), voucher.getPrice(), voucher.getDuration())).toList();

            boolean hasTextTherapy = vouchers.stream().anyMatch(voucher -> voucher.voucherType() == VoucherType.TEXT_THERAPY);
            boolean hasVoiceTherapy = vouchers.stream().anyMatch(voucher -> voucher.voucherType() == VoucherType.VOICE_THERAPY);
            boolean hasVideoTherapy = vouchers.stream().anyMatch(voucher -> voucher.voucherType() == VoucherType.VIDEO_THERAPY);

            return new ExpertDTORecord(expert.getExpertId(), expert.getName(), expert.getProfileImage(), expert.getExpertTitle(), vouchers, hasTextTherapy, hasVoiceTherapy, hasVideoTherapy);
        }).toList();

        return new ClientMainDTORecord(comms, experts);
    }

    // 익스퍼트 메인
    @Transactional
    public ExpertMainDTORecord getExpertMain(Integer expertId) {
        List<RecentReviewRecord> recentReviewRecords = reviewRepository.findReviewRecordsByExpertId(expertId);
        List<CounselScheduleRecord> counselScheduleRecords = counselRepository.findCounselScheduleRecordsByExpertId(expertId);

        return new ExpertMainDTORecord(recentReviewRecords, counselScheduleRecords);
    }

    // 오어스 회원가입
    @Transactional
    public SessionUser loginKakao(String code, RedisTemplate<String, Object> redisTemp) {
        String userRoleStr = (String) redisTemp.opsForValue().get("userRole");
        UserRole userRole = UserRole.valueOf(userRoleStr.toUpperCase());

        RestTemplate restTemp = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // 1.3 http body 설정
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", "f07259c71010e17f9a081c435bc8328b");
        body.add("redirect_uri", "http://localhost:8080/oauth/callback/kakao");
        body.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        ResponseEntity<UserResponse.KakaoTokenDTO> response = restTemp.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST, request, UserResponse.KakaoTokenDTO.class);

        // 1.6 값 확인
        System.out.println(response.getBody().toString());

        // 2. 토큰으로 사용자 정보 받기 (PK, Email)
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer " + response.getBody().getAccessToken());

        HttpEntity<Void> request2 = new HttpEntity<>(headers2);
        ResponseEntity<UserResponse.KakaoUserDTO> response2 = restTemp.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.GET, request2, UserResponse.KakaoUserDTO.class);

        System.out.println("response2 : " + response2.getBody().toString());

        // 3. 사용자 정보 가져오기
        UserResponse.KakaoUserDTO kakaoUser = response2.getBody();
        String email = kakaoUser.getKakaoAccount().getEmail();
        String nickname = kakaoUser.getKakaoAccount().getProfile().getNickname();
        Long kakaoId = kakaoUser.getId();
        String accessToken = response.getBody().getAccessToken();

        User userPS = null;

        if (email != null) {
            // 이메일이 있는 경우
            userPS = userRepository.findByEmail(email);
        } else {
            // 이메일이 없는 경우, 카카오 ID로 사용자 찾기
            userPS = userRepository.findByEmail("kakao_" + kakaoId + "@kakao.com");
        }

        // 4. 있으면? - 조회된 유저정보 리턴
        if (userPS != null) {
            return new SessionUser(userPS, accessToken);
        } else {
            System.out.println("어? 유저가 없네? 강제회원가입 and 강제로그인 진행");
            // 5. 없으면? - 강제 회원가입
            User user = User.builder()
                    .name(nickname)
                    .password(UUID.randomUUID().toString())
                    .email(email != null ? email : "kakao_" + kakaoId + "@kakao.com")
                    .phone("000-0000-0000")
                    .userRole(userRole)
                    .profileImage(kakaoUser.getKakaoAccount().getProfile().toString())
                    .expertTitle(UserDefault.getDefaultExpertTitle())
                    .provider(OauthProvider.KAKAO)
                    .build();
            User returnUser = userRepository.save(user);
            return new SessionUser(returnUser, accessToken);
        }
    }

    @Transactional
    public SessionUser loginNaver(String code, String state, RedisTemplate<String, Object> redisTemp) {
        String userRoleStr = (String) redisTemp.opsForValue().get("userRole");
        UserRole userRole = UserRole.valueOf(userRoleStr.toUpperCase());

        RestTemplate restTemp = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", "elWt0DvVScIBARwZfyU7");
        body.add("client_secret", "iQ7E21zhDQ");
        body.add("code", code);
        body.add("state", state);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        ResponseEntity<UserResponse.NaverTokenDTO> response = restTemp.exchange("https://nid.naver.com/oauth2.0/token", HttpMethod.POST, request, UserResponse.NaverTokenDTO.class);

        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer " + response.getBody().getAccessToken());
        HttpEntity<Void> request2 = new HttpEntity<>(headers2);
        ResponseEntity<UserResponse.NaverUserDTO> response2 = restTemp.exchange("https://openapi.naver.com/v1/nid/me", HttpMethod.GET, request2, UserResponse.NaverUserDTO.class);

        UserResponse.NaverUserDTO naverUser = response2.getBody();
        if (naverUser == null || naverUser.getResponse() == null) {
            throw new IllegalStateException("네이버 계정 정보를 불러오지 못했습니다.");
        }

        String email = naverUser.getResponse().getEmail();
        UserResponse.NaverUserDTO.Response.Profile profile = naverUser.getResponse().getProfile();
        String nickname = (profile != null) ? profile.getNickname() : "NaverUser";
        Long naverId = naverUser.getId();
        String accessToken = response.getBody().getAccessToken();

        User userPS = null;
        if (email != null) {
            userPS = userRepository.findByEmail(email);
        } else {
            userPS = userRepository.findByEmail("naver_" + naverId + "@naver.com");
        }

        if (userPS != null) {
            return new SessionUser(userPS, accessToken);
        } else {
            User user = User.builder()
                    .name(nickname)
                    .password(UUID.randomUUID().toString())
                    .email(email != null ? email : "naver_" + naverId + "@naver.com")
                    .phone("000-0000-0000")
                    .userRole(userRole)
                    .profileImage(UserDefault.getDefaultProfileImage())
                    .expertTitle(UserDefault.getDefaultExpertTitle())
                    .provider(OauthProvider.NAVER)
                    .build();
            User returnUser = userRepository.save(user);
            return new SessionUser(returnUser, accessToken);
        }
    }

    public boolean logoutKakao(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    "https://kapi.kakao.com/v1/user/logout",
                    HttpMethod.POST,
                    request,
                    String.class
            );
            System.out.println("Kakao logout response: " + response.getBody());
            return true;
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                System.out.println("Kakao logout failed: Unauthorized");
            } else {
                System.out.println("Kakao logout failed: " + e.getStatusCode());
            }
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean logoutNaver(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    "https://nid.naver.com/oauth2.0/token?grant_type=delete&client_id=elWt0DvVScIBARwZfyU7&client_secret=iQ7E21zhDQ&access_token=" + accessToken + "&service_provider=NAVER",
                    HttpMethod.GET,
                    request,
                    String.class
            );
            System.out.println("Naver logout response: " + response.getBody());
            return true;
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                System.out.println("Naver logout failed: Unauthorized");
            } else {
                System.out.println("Naver logout failed: " + e.getStatusCode());
            }
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

