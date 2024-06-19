package com.example.aboutme.comm;

import com.example.aboutme.comm.enums.CommCategory;
import com.example.aboutme.user.User;
import lombok.Data;

import java.sql.Timestamp;

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
        private Integer commId;
        private User user; // 조인해야 할 듯.
        private String content;
        private String title;
        private CommCategory category;
        private Timestamp createdAt;

        public CommDetailDTO(Comm comm) {
            this.commId = comm.getId();
            this.user = comm.getUser();
            this.content = comm.getContent();
            this.title = comm.getTitle();
            this.category = comm.getCategory();
            this.createdAt = comm.getCreatedAt();
        }
    }
}
