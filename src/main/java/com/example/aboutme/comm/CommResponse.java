package com.example.aboutme.comm;

import com.example.aboutme.comm.enums.CommCategory;
import com.example.aboutme.user.enums.UserRole;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

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
    public static class CommDetailDTO {
        private Integer id;
        private String clientImage;
        private String name;
        private String content;
        private String title;
        private String category;
        private Timestamp createdAt;
        private List<String> replyContents;
        private Map<String, List<CommDTO>> commsByCategory;

        public CommDetailDTO(Integer id, String clientImage, String name, String content, String title, String category, Timestamp createdAt, List<String> replyContents, Map<String, List<CommDTO>> commsByCategory) {
            this.id = id;
            this.clientImage = clientImage;
            this.name = name;
            this.content = content;
            this.title = title;
            this.category = category;
            this.createdAt = createdAt;
            this.replyContents = replyContents;
            this.commsByCategory = commsByCategory;
        }
    }

    @Data
    public static class CommDTO {
        private Integer id;
        private String content;
        private String title;
        private String category;
        private Timestamp createdAt;

        public CommDTO(Integer id, String content, String title, String category, Timestamp createdAt) {
            this.id = id;
            this.content = content;
            this.title = title;
            this.category = category;
            this.createdAt = createdAt;
        }
    }

    @Data
    public static class CommAndReplyDTO {
        private Integer id;
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
        public CommAndReplyDTO(Integer id, String title, String content, CommCategory category, String userProfileImage, String writerName,
                               UserRole userRole, String replyProfileImage, String expertName, String solution) {
            this.id = id;
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
