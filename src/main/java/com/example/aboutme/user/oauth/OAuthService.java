//package com.example.aboutme.user.oauth;
//
//import com.example.aboutme._core.utils.UserDefault;
//import com.example.aboutme.user.User;
//import com.example.aboutme.user.enums.OauthProvider;
//import com.example.aboutme.user.enums.UserRole;
//import com.example.aboutme.user.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//import jakarta.servlet.http.HttpSession;
//import java.util.UUID;
//
//@Service
//public abstract class OAuthService {
//
//    @Autowired
//    protected UserRepository userRepository;
//
//    protected abstract String getProviderName();
//    protected abstract String getTokenUrl();
//    protected abstract String getUserInfoUrl();
//    protected abstract MultiValueMap<String, String> getTokenRequestBody(String code, String state);
//    protected abstract UserResponse getUserInfo(ResponseEntity<?> response);
//    protected abstract OauthProvider getProvider();
//    protected abstract String getClientId();
//    protected abstract String getClientSecret();
//
//    public User login(String code, String state, HttpSession session) {
//        // 1. 선택된 유저 롤
//        String userRoleStr = (String) session.getAttribute("userRole");
//        UserRole userRole = UserRole.valueOf(userRoleStr.toUpperCase());
//
//        // 1.1 RestTemplate 설정
//        RestTemplate rt = new RestTemplate();
//
//        // 1.2 http header 설정
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//
//        // 1.3 http body 설정
//        MultiValueMap<String, String> body = getTokenRequestBody(code, state);
//
//        // 1.4 body+header 객체 만들기
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
//
//        // 1.5 api 요청하기 (토큰 받기)
//        ResponseEntity<?> response = rt.exchange(
//                getTokenUrl(),
//                HttpMethod.POST,
//                request,
//                getResponseTokenDTOClass());
//
//        // 1.6 값 확인
//        System.out.println(response.getBody().toString());
//
//        // 2. 토큰으로 사용자 정보 받기 (PK, Email)
//        HttpHeaders headers2 = new HttpHeaders();
//        headers2.add("Authorization", "Bearer " + getAccessToken(response));
//
//        HttpEntity<Void> request2 = new HttpEntity<>(headers2);
//
//        ResponseEntity<?> response2 = rt.exchange(
//                getUserInfoUrl(),
//                HttpMethod.GET,
//                request2,
//                getResponseUserDTOClass());
//
//        System.out.println("response2 : " + response2.getBody().toString());
//
//        // 3. 사용자 정보 가져오기
//        UserResponse userResponse = getUserInfo(response2);
//
//        // userResponse 또는 userResponse.getResponse()가 null인지 확인
//        if (userResponse == null || userResponse.getResponse() == null) {
//            throw new IllegalStateException(getProviderName() + " 계정 정보를 불러오지 못했습니다.");
//        }
//
//        String email = userResponse.getEmail();
//        UserResponse.Profile profile = userResponse.getProfile();
//        String nickname = (profile != null) ? profile.getNickname() : getProviderName() + "User";
//        Long userId = userResponse.getId();
//
//        User userPS = null;
//
//        if (email != null) {
//            // 이메일이 있는 경우
//            userPS = userRepository.findByEmail(email);
//        } else {
//            // 이메일이 없는 경우, 프로바이더 ID로 사용자 찾기
//            userPS = userRepository.findByEmail(getProviderName().toLowerCase() + "_" + userId + "@" + getProviderName().toLowerCase() + ".com");
//        }
//
//        // 4. 있으면? - 조회된 유저정보 리턴
//        if (userPS != null) {
//            System.out.println("어? 유저가 있네? 강제로그인 진행시켜!");
//            return userPS;
//
//            // 5-2. 사용자 정보가 DB에 없으면??
//        } else {
//            System.out.println("어? 유저가 없네? 강제회원가입 and 강제로그인 진행시켜!!");
//
//            // 5. 없으면? - 강제 회원가입
//            User user = User.builder()
//                    .name(nickname)
//                    .password(UUID.randomUUID().toString())
//                    .email(email != null ? email : getProviderName().toLowerCase() + "_" + userId + "@" + getProviderName().toLowerCase() + ".com")
//                    .phone("000-0000-0000")
//                    .userRole(userRole)
//                    .profileImage(UserDefault.getDefaultProfileImage())
//                    .expertTitle(UserDefault.getDefaultExpertTitle())
//                    .provider(getProvider())
//                    .build();
//            User returnUser = userRepository.save(user);
//            return returnUser;
//        }
//    }
//
//    protected abstract Class<?> getResponseTokenDTOClass();
//    protected abstract Class<?> getResponseUserDTOClass();
//    protected abstract String getAccessToken(ResponseEntity<?> response);
//}
