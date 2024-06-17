package com.example.aboutme.user;

import lombok.Builder;
import lombok.Data;

@Data
public class SessionUser {
    private Integer userId;
    private String username;
    private String email;

    @Builder
    public SessionUser(Integer userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    public SessionUser(User user) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }
}
