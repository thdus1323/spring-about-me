package com.example.aboutme.user;

import com.example.aboutme.comm.enums.CommCategory;
import com.example.aboutme.voucher.enums.VoucherType;
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
        public static class VoucherImageDTO {
            public String url;

            public VoucherImageDTO(String url) {
                this.url = url;
            }
        }
    }

//    // 클라이언트 메인
//    @Data
//    public static class ExpertDTO {
//        private Integer expertId;
//        private String profileImage;
//        private String name;
//        private String expertTitle;
//        private List<VoucherDTO> vouchers;
//
//        public ExpertDTO(Integer expertId, String profileImage, String name, String expertTitle, List<VoucherDTO> vouchers) {
//            this.expertId = expertId;
//            this.profileImage = profileImage;
//            this.name = name;
//            this.expertTitle = expertTitle;
//            this.vouchers = vouchers;
//        }
//    }
//
//    @Data
//    public static class VoucherDTO {
//        private Integer voucherId;
//        private String voucherType;
//
//        public VoucherDTO(Integer voucherId, String voucherType) {
//            this.voucherId = voucherId;
//            this.voucherType = voucherType;
//        }
//    }

    // 클라이언트 메인
    @Data
    public static class ClientMainDTO {
        @Data
        public static class CommDTO {
            private Integer communityId;
            private String title;
            private String content;
            private String category;
            private String writerImage;
            private String writerName;
            private String expertImage;
            private String expertName;

            public CommDTO(Integer communityId, String title, String content, CommCategory category,
                           String writerImage, String writerName, String expertImage, String expertName) {
                this.communityId = communityId;
                this.title = title;
                this.content = content;
                this.category = category.getKorean();
                this.writerImage = writerImage;
                this.writerName = writerName;
                this.expertImage = expertImage;
                this.expertName = expertName;
            }
        }

        @Data
        public static class ExpertDTO {
            private Integer expertId;
            private String profileImage;
            private String name;
            private String expertTitle;

            public ExpertDTO(Integer expertId, String profileImage, String name, String expertTitle) {
                this.expertId = expertId;
                this.profileImage = profileImage;
                this.name = name;
                this.expertTitle = expertTitle;
            }
        }

        @Data
        public static class VoucherDTO {
            private Integer voucherId;
            private VoucherType voucherType;
            private Integer expertId;

            public VoucherDTO(Integer voucherId, VoucherType voucherType, Integer expertId) {
                this.voucherId = voucherId;
                this.voucherType = voucherType;
                this.expertId = expertId;
            }
        }
    }
}