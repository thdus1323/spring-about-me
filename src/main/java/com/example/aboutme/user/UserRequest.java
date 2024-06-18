package com.example.aboutme.user;

import lombok.Data;

public class UserRequest {
    @Data
    public static class JoinDTO{
        private String email;
        private String password;
        private String name;
    }

    @Data
    public static class LoginDTO{
        private String name;
        private String password;
    }
}
