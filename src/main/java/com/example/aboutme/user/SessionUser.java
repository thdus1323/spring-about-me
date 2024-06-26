package com.example.aboutme.user;

import com.example.aboutme.user.enums.OauthProvider;
import com.example.aboutme.user.enums.UserRole;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class SessionUser {
    private Integer id;
    private String name;
    private String email;
    private String expertTitle;
    private UserRole userRole;
    private OauthProvider provider;
    private String accessToken;
    private String profileImage;

    // 추가 필드
    private boolean isClient;
    private boolean isExpert;

    public void determineRoles() {
        this.isClient = this.userRole == UserRole.CLIENT;
        this.isExpert = this.userRole == UserRole.EXPERT;
    }

    @Builder
    public SessionUser(Integer id, String name, String email, String expertTitle, UserRole userRole, OauthProvider provider, String accessToken, String profileImage) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.expertTitle = expertTitle;
        this.userRole = userRole;
        this.provider = provider;
        this.accessToken = accessToken;
        this.profileImage = profileImage;
    }

    public SessionUser(User user, String accessToken) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.profileImage = user.getProfileImage();
        this.expertTitle = user.getExpertTitle();
        this.userRole = user.getUserRole();
        this.provider = user.getProvider();
        this.accessToken = accessToken;
    }

    public SessionUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.expertTitle = user.getExpertTitle();
        this.profileImage = user.getProfileImage();
        this.userRole = user.getUserRole();
        this.provider = user.getProvider();
    }
}