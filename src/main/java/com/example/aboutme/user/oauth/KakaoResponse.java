package com.example.aboutme.user.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

public class KakaoResponse {

    @Data
    public static class TokenDTO {
        @JsonProperty("access_token")
        private String accessToken;

        @JsonProperty("token_type")
        private String tokenType;

        @JsonProperty("refresh_token")
        private String refreshToken;

        @JsonProperty("expires_in")
        private int expiresIn;

        @JsonProperty("scope")
        private String scope;
    }

    @Data
    public static class KakaoUserDTO {
        private Long id;

        @JsonProperty("kakao_account")
        private KakaoAccount kakaoAccount;

        @Data
        public static class KakaoAccount {
            private String email;
            private Profile profile;

            @Data
            public static class Profile {
                private String nickname;
            }
        }
    }
}