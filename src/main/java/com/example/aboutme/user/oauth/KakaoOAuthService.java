//package com.example.aboutme.user.oauth;
//
//import com.example.aboutme.user.enums.OauthProvider;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//
//@Service
//public class KakaoOAuthService extends OAuthService {
//
//    @Override
//    protected String getProviderName() {
//        return "kakao";
//    }
//
//    @Override
//    protected String getTokenUrl() {
//        return "https://kauth.kakao.com/oauth/token";
//    }
//
//    @Override
//    protected String getUserInfoUrl() {
//        return "https://kapi.kakao.com/v2/user/me";
//    }
//
//    @Override
//    protected MultiValueMap<String, String> getTokenRequestBody(String code, String state) {
//        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
//        body.add("grant_type", "authorization_code");
//        body.add("client_id", getClientId());
//        body.add("redirect_uri", "http://localhost:8080/oauth/callback/kakao");
//        body.add("code", code);
//        return body;
//    }
//
//    @Override
//    protected UserResponse getUserInfo(ResponseEntity<?> response) {
//        return (UserResponse) response.getBody();
//    }
//
//    @Override
//    protected OauthProvider getProvider() {
//        return OauthProvider.KAKAO;
//    }
//
//    @Override
//    protected String getClientId() {
//        return "f07259c71010e17f9a081c435bc8328b";
//    }
//
//    @Override
//    protected String getClientSecret() {
//        return "YOUR_KAKAO_CLIENT_SECRET";
//    }
//
//    @Override
//    protected Class<?> getResponseTokenDTOClass() {
//        return UserResponse.KakaoTokenDTO.class;
//    }
//
//    @Override
//    protected Class<?> getResponseUserDTOClass() {
//        return UserResponse.KakaoUserDTO.class;
//    }
//
//    @Override
//    protected String getAccessToken(ResponseEntity<?> response) {
//        UserResponse.KakaoTokenDTO tokenResponse = (UserResponse.KakaoTokenDTO) response.getBody();
//        return tokenResponse.getAccessToken();
//    }
//}
