package com.example.aboutme.user;

import com.example.aboutme.user.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SessionUser {
    private Integer id;
    private String name;
    private String email;
    private UserRole userRole;

    @Builder
    public SessionUser(Integer id, String name, String email, UserRole userRole) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userRole = userRole;
    }

    public SessionUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.userRole = user.getUserRole();
    }
}
