package com.example.aboutme.chat;

public class ChatMessage {
    private String sender;
    private String profileImage;
    private String content;
    private Integer id;
    private Integer voucherId;

    public ChatMessage() {
    }

    public ChatMessage(String sender, String profileImage, String content, Integer id, Integer voucherId) {
        this.sender = sender;
        this.profileImage = profileImage;
        this.content = content;
        this.id = id;
        this.voucherId = voucherId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
    }
}