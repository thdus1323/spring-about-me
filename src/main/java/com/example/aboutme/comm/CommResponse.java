package com.example.aboutme.comm;

import com.example.aboutme.comm.enums.CommCategory;
import com.example.aboutme.reply.Reply;
import com.example.aboutme.user.enums.UserRole;
import kotlin.jvm.Transient;
import lombok.Data;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    // 게시글 디테일 DTO -> Comm, Reply
    @Data
    public static class CommWithRepliesDTO {
        private Integer id;
        private String writerName;
        private String writerImage;
        private String content;
        private String title;
        private String category;
        private Timestamp createdAt;
        private List<BothReplyDTO> replies = new ArrayList<>();
        @Transient
        private String timeAgo;

        public CommWithRepliesDTO(Comm comm) {
            this.id = comm.getId();
            this.writerName = comm.getUser().getName();
            this.writerImage = comm.getUser().getProfileImage();
            this.content = comm.getContent();
            this.title = comm.getTitle();
            this.category = comm.getCategory().getKorean();
            this.createdAt = comm.getCreatedAt();
            this.replies = comm.getReplies() == null ? new ArrayList<>() : comm.getReplies().stream()
                    .filter(reply -> reply.getUser() != null)
                    .map(BothReplyDTO::new)
                    .collect(Collectors.toList());
        }

        public void calculateTimeAgo() {
            Duration duration = Duration.between(this.createdAt.toInstant(), Instant.now());
            long seconds = duration.getSeconds();

            if (seconds < 60) {
                this.timeAgo = seconds + "초 전";
            } else if (seconds < 3600) {
                this.timeAgo = (seconds / 60) + "분 전";
            } else if (seconds < 86400) {
                this.timeAgo = (seconds / 3600) + "시간 전";
            } else {
                this.timeAgo = (seconds / 86400) + "일 전";
            }
        }

        @Data
        public static class BothReplyDTO {
            private Integer id;
            private String profileImage;
            private String name;
            private boolean userRole;
            private String content; // 일반인 답글
            private String introduction; // 소개글
            private String summary; // 사연 요약
            private String causeAnalysis; // 원인 분석
            private String solution; // 대처 방향


            public BothReplyDTO(Reply reply) {
                this.id = reply.getId();
                this.profileImage = reply.getUser().getProfileImage();
                this.name = reply.getUser().getName();
                this.userRole = reply.getUser().getUserRole().equals(UserRole.EXPERT);
                this.introduction = reply.getIntroduction();
                this.summary = reply.getSummary();
                this.causeAnalysis = reply.getCauseAnalysis();
                this.solution = reply.getSolution();
                this.content = reply.getContent();
            }
        }
    }

    // 모든 글과 댓글 가져와서 커뮤니티 메인에 뿌릴 DTO
    @Data
    public static class ALLCommWithRepliesDTO {
        private Integer id;
        private String writerName;
        private String writerImage;
        private String content;
        private String title;
        private String category;
        private int replies;
        private List<ExpertReplyDTO> expertReplies = new ArrayList<>();

        public ALLCommWithRepliesDTO(Comm comm) {
            this.id = comm.getId();
            this.writerName = comm.getUser().getName();
            this.writerImage = comm.getUser().getProfileImage();
            this.content = comm.getContent();
            this.title = comm.getTitle();
            this.category = comm.getCategory().getKorean();
            this.replies = (int) comm.getReplies().stream().count();
            this.expertReplies = comm.getReplies() == null ? new ArrayList<>() : comm.getReplies().stream()
                    .filter(reply -> reply.getUser() != null)
                    .map(ExpertReplyDTO::new)
                    .collect(Collectors.toList());
        }

        @Data
        public static class ExpertReplyDTO {
            private Integer id;
            private String solution;
            private String profileImage;
            private String expertName;
            private boolean userRole;

            public ExpertReplyDTO(Reply reply) {
                this.id = reply.getId();
                this.solution = reply.getSolution();
                this.profileImage = reply.getUser().getProfileImage();
                this.expertName = reply.getUser().getName();
                this.userRole = reply.getUser().getUserRole().equals(UserRole.EXPERT);
            }
        }
    }

}
