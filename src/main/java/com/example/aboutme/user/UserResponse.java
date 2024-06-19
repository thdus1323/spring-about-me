package com.example.aboutme.user;

import com.example.aboutme.counsel.Counsel;
import com.example.aboutme.user.pr.PR;
import com.example.aboutme.voucher.Voucher;
import lombok.Data;

import java.util.List;

public class UserResponse {


    // 상담사 리스트DTO
    @Data
    public static class ExpertUserDTO {
        public Integer expertId;
        public String name;
        public String title;
        public List<VoucherImageDTO> voucherImage;
        public String profileImage;

        public ExpertUserDTO(User user, List<VoucherImageDTO> voucherImage) {
            this.expertId = user.getId();
            this.name = user.getName();
            this.title = user.getExpertTitle();
            this.voucherImage = voucherImage;
            this.profileImage = user.getProfileImage();
        }

        @Data
        public static class VoucherImageDTO{
            public String url;

            public VoucherImageDTO(String url) {
                this.url = url;
            }
        }
    }
}
