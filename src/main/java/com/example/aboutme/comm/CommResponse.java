package com.example.aboutme.comm;

import com.example.aboutme.comm.enums.CommCategory;
import com.example.aboutme.user.enums.UserRole;
import lombok.Data;

public class CommResponse {

    @Data
    public static class ClientMainCommListDTO {
        private Integer communityId;
        private String title;
        private String content;
        private String category;
        private String writerImage;
        private String writerName;
        private String expertImage;
        private String expertName;

        public ClientMainCommListDTO(Integer communityId, String title, String content, CommCategory category,
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
    public static class CommAndReplyDTO {
        private String title;
        private String content;
        private String category;
        private String userProfileImage;
        private String writerName;
        private boolean userRole;
        private String replyProfileImage;
        private String expertName;
        private String solution;

        // 생성자 잘 확인해야함. EXPERT면 true 나와서 출력할 수 있도록.
        public CommAndReplyDTO(String title, String content, CommCategory category, String userProfileImage, String writerName,
                               UserRole userRole, String replyProfileImage, String expertName, String solution) {
            this.title = title;
            this.content = content;
            this.category = category.getKorean();
            this.userProfileImage = userProfileImage;
            this.writerName = writerName;
            this.userRole = userRole == UserRole.EXPERT ? true : false;
            this.replyProfileImage = replyProfileImage;
            this.expertName = expertName;
            this.solution = solution;
        }
    }
}
