package com.example.aboutme.user;

import lombok.Builder;
import lombok.Data;

@Data
public class SessionUser {
    private Integer userId;
    private String name;
    private String email;

    @Builder
    public SessionUser(Integer userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public SessionUser(User user) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
}
