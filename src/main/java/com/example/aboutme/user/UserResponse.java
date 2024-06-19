package com.example.aboutme.user;

import com.example.aboutme.comm.enums.CommCategory;
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

    // 클라이언트 메인
    @Data
    public static class ExpertDTO {
        private Integer expertId;
        private String profileImage;
        private String name;
        private String expertTitle;
        private List<VoucherDTO> vouchers;

        public ExpertDTO(Integer expertId, String profileImage, String name, String expertTitle, List<VoucherDTO> vouchers) {
            this.expertId = expertId;
            this.profileImage = profileImage;
            this.name = name;
            this.expertTitle = expertTitle;
            this.vouchers = vouchers;
        }
    }

    @Data
    public static class VoucherDTO {
        private Integer voucherId;
        private String voucherType;

        public VoucherDTO(Integer voucherId, String voucherType) {
            this.voucherId = voucherId;
            this.voucherType = voucherType;
        }
    }
}
