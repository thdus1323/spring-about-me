package com.example.aboutme.user.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

public class UserResponse {

    @Data
    public static class KakaoTokenDTO {
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

    @Data
    public static class NaverTokenDTO {
        @JsonProperty("access_token")
        private String accessToken;

        @JsonProperty("token_type")
        private String tokenType;

        @JsonProperty("refresh_token")
        private String refreshToken;

        @JsonProperty("expires_in")
        private Integer expiresIn;

        @JsonProperty("scope")
        private String scope;
    }

    @Data
    public static class NaverUserDTO {
        private Long id;
        private String resultcode;
        private String message;
        @JsonProperty("response")
        private Response response;

        @Data
        public static class Response {
            private String email;
            private Profile profile;

            @Data
            public static class Profile {
                private String nickname;
            }
        }
    }
}
