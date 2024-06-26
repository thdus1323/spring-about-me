package com.example.aboutme.reply;

import lombok.Builder;
import lombok.Data;

public class ReplyResponse {

    // 전문가 댓글 작성 폼에서 받아온 데이터 DTO
    @Data
    public static class ReplyDataDTO {
        private String id;
        private String introduction;
        private String summary;
        private String analysis;
        private String solution;

        @Builder
        public ReplyDataDTO(String id, String introduction, String summary, String analysis, String solution) {
            this.id = id;
            this.introduction = introduction;
            this.summary = summary;
            this.analysis = analysis;
            this.solution = solution;
        }
    }

    // 일반인 댓글 작성 DTO
    @Data
    public static class ClientReplyDataDTO {
        private String id;
        private String content;

        @Builder
        public ClientReplyDataDTO(String id, String content) {
            this.id = id;
            this.content = content;
        }
    }
}
