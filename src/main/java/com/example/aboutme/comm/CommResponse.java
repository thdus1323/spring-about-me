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


import static java.util.stream.Collectors.toList;


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
    public static class CommWithRepliesDTO {
        private Integer id;
        private String writerName;
        private String content;
        private String title;
        private String category;
        private int replies;
        private List<ExpertReplyDTO> expertReplies = new ArrayList<>();

        public CommWithRepliesDTO(Comm comm) {
            this.id = comm.getId();
            this.writerName = comm.getUser().getName();
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

    //<--커뮤니티 detail 시작-->
    @Data
    public static class CommDetailDTO {
        private CommDTO commDTO;
        private List<ReplyDTO> replies = new ArrayList<>();
        private List<RelatedCommWithRepliesDTO> relatedComms = new ArrayList<>();

        public CommDetailDTO(Comm comm, List<Reply> replies, List<Comm> relatedComms) {
            this.commDTO = new CommDTO(comm);
            this.replies = replies.stream()
                    .map(ReplyDTO::new)
                    .toList();
            this.relatedComms = relatedComms.stream()
                    .map(RelatedCommWithRepliesDTO::new)
                    .toList();

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

        @Data
        public static class CommDTO {
            private Integer id;
            private String title;
            private String content;
            private String category;
            private String writerName;
            private String writerProfileImage;
            private Timestamp createdAt;
            //            private int likeCount;
            private int replyCount;

            public CommDTO(Comm comm) {
                this.id = comm.getId();
                this.title = comm.getTitle();
                this.content = comm.getContent();
                this.category = comm.getCategory().getKorean();
                this.writerName = comm.getUser().getName();
                this.writerProfileImage = comm.getUser().getProfileImage();
                this.createdAt = comm.getCreatedAt();
//                this.likeCount = comm.getLikes().size();
                this.replyCount = comm.getReplies().size();
            }
        }

        @Data
        public static class ReplyDTO {
            private Integer id;
            private String content;
            private String solution;
            private Timestamp createdAt;

            public ReplyDTO(Reply reply) {
                this.id = reply.getId();
                this.content = reply.getContent();
                this.content = reply.getSolution();
                this.createdAt = reply.getCreatedAt();
            }
        }

        @Data
        public static class RelatedCommWithRepliesDTO {
            private Integer id;
            private String content;
            private String title;
            private String category;
            private Timestamp createdAt;
            private int replies;
            private String writerProfileImage;
            private String writerName;
            private List<ExpertReplyDTO> expertReplies = new ArrayList<>();

            public RelatedCommWithRepliesDTO(Comm comm) {
                this.id = comm.getId();
                this.content = comm.getContent();
                this.title = comm.getTitle();
                this.category = comm.getCategory().getKorean();
                this.createdAt = comm.getCreatedAt();
                this.writerProfileImage = comm.getUser().getProfileImage();
                this.writerName = comm.getUser().getName();
                this.replies = (int) comm.getReplies().stream()
                        .filter(reply -> !reply.getUser().getUserRole().equals(UserRole.CLIENT))
                        .count();
                this.expertReplies = comm.getReplies().stream()
                        .filter(reply -> reply.getUser().getUserRole().equals(UserRole.EXPERT))
                        .map(ExpertReplyDTO::new)
                        .collect(toList());
            }

            @Data
            public static class ExpertReplyDTO {
                private Integer id;
                private String solution;

                public ExpertReplyDTO(Reply reply) {
                    this.id = reply.getId();
                    this.solution = reply.getSolution();
                }
            }
        }
    }
    //<--커뮤니티 detail 끝-->

}
