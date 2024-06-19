package com.example.aboutme.user;

import lombok.Data;

public class UserResponse {


    // 상담사 리스트DTO
    @Data
    public static class ExpertUserDTO {
        public String nickname;
        public String title;
        public String voucherImage;
        public String expertImage;
//        public String
    }
}
