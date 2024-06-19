package com.example.aboutme.comm;

import com.example.aboutme.reply.Reply;
import com.example.aboutme.user.User;
import lombok.Builder;
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

        @Builder
        public ClientMainCommListDTO(Comm comm, User client, Reply expert) {
            this.communityId = comm.getId();
            this.title = comm.getTitle();
            this.content = comm.getContent();
            this.category = comm.getCategory().getKorean();
            this.writerImage = client.getProfileImage();
            this.writerName = client.getName();
            this.expertImage = expert.getUser().getProfileImage();
            this.expertName = expert.getUser().getName();
        }
    }
}
