package com.example.aboutme.comm;


import lombok.Data;

public class CommRequest {

    // 받아온 게시글 정보 DTO
    @Data
    public static class RequestCommDTO {
        private String title;
        private String content;
        private String category;

        public RequestCommDTO() {
        }

        public RequestCommDTO(String title, String content, String category) {
            this.title = title;
            this.content = content;
            this.category = category;
        }
    }

    // 수정될 게시글 DTO
    @Data
    public static class UpdateRequestCommDTO {
        private Integer id;
        private String title;
        private String content;
        private String category;

        public UpdateRequestCommDTO() {
        }

        public UpdateRequestCommDTO(Integer id, String title, String content, String category) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.category = category;
        }
    }
}
