package com.example.aboutme.reply;

import lombok.Data;

public class ReplyRequest {

    @Data
    public static class DeleteReplyDTO {
        private Integer writerId;
        private Integer replyId;

        public DeleteReplyDTO(Integer writerId, Integer replyId) {
            this.writerId = writerId;
            this.replyId = replyId;
        }

        public DeleteReplyDTO() {
        }
    }
}
