package com.example.aboutme.comm;

import com.example.aboutme.comm.enums.CommCategory;
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
}
