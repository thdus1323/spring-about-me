package com.example.aboutme.user;

import com.example.aboutme.user.enums.UserRole;
import lombok.Data;

public class UserRequest {
    @Data
    public static class JoinDTO {
        private String email;
        private String password;
        private String name;
    }

    @Data
    public static class LoginDTO {
        private String email;
        private String password;
        private UserRole userRole;
    }
}
