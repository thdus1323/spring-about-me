package com.example.aboutme.chat;


import lombok.Data;

@Data
public class ChatReqDTO {
    private String sender;
    private String profileImage;
    private String content;
    private Integer id;
    private Integer counselId;

    public ChatReqDTO(String sender, String profileImage, String content, Integer id, Integer counselId) {
        this.sender = sender;
        this.profileImage = profileImage;
        this.content = content;
        this.id = id;
        this.counselId = counselId;
    }

}