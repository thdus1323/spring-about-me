package com.example.aboutme.reply;

import lombok.Data;

public class ReplyResponse {

    // 전문가 댓글 작성 폼에서 받아온 데이터 저장할 객체
    @Data
    public static class ReplyDataDTO{
        private String id;
        private String introduction;
        private String summary;
        private String analysis;
        private String solution;

        public ReplyDataDTO(String id, String introduction, String summary, String analysis, String solution) {
            this.id = id;
            this.introduction = introduction;
            this.summary = summary;
            this.analysis = analysis;
            this.solution = solution;
        }
    }
}
