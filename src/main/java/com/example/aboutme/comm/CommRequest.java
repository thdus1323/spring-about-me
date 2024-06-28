package com.example.aboutme.comm;

import kotlin.jvm.Transient;
import lombok.Data;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;

public class CommRequest {

    // 받아온 게시글 정보 DTO
    @Data
    public static class RequestCommDTO {
        private String title;
        private String content;
        private String category;

        public RequestCommDTO(String title, String content, String category) {
            this.title = title;
            this.content = content;
            this.category = category;
        }
    }

    // TODO : 수정 필요
    // 수정될 게시글 DTO
    @Data
    public static class UpdateRequestCommDTO {
        private String id;
        private String title;
        private String content;
        private String category;

        public UpdateRequestCommDTO(String id, String title, String content, String category) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.category = category;
        }
    }
}
