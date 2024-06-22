//package com.example.aboutme.user.oauth;
//
//import com.example.aboutme.user.enums.OauthProvider;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//
//@Service
//public class NaverOAuthService extends OAuthService {
//
//    @Override
//    protected String getProviderName() {
//        return "naver";
//    }
//
//    @Override
//    protected String getTokenUrl() {
//        return "https://nid.naver.com/oauth2.0/token";
//    }
//
//    @Override
//    protected String getUserInfoUrl() {
//        return "https://openapi.naver.com/v1/nid/me";
//    }
//
//    @Override
//    protected MultiValueMap<String, String> getTokenRequestBody(String code, String state) {
//        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
//        body.add("grant_type", "authorization_code");
//        body.add("client_id", getClientId());
//        body.add("client_secret", getClientSecret());
//        body.add("code", code);
//        body.add("state", state);
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
//        return OauthProvider.NAVER;
//    }
//
//    @Override
//    protected String getClientId() {
//        return "elWt0DvVScIBARwZfyU7";
//    }
//
//    @Override
//    protected String getClientSecret() {
//        return "iQ7E21zhDQ";
//    }
//
//    @Override
//    protected Class<?> getResponseTokenDTOClass() {
//        return UserResponse.NaverTokenDTO.class;
//    }
//
//    @Override
//    protected Class<?> getResponseUserDTOClass() {
//        return UserResponse.NaverUserDTO.class;
//    }
//
//    @Override
//    protected String getAccessToken(ResponseEntity<?> response) {
//        UserResponse.NaverTokenDTO tokenResponse = (UserResponse.NaverTokenDTO) response.getBody();
//        return tokenResponse.getAccessToken();
//    }
//}
