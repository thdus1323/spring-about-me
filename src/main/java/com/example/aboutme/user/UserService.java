package com.example.aboutme.user;

import com.example.aboutme.user.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserNativeRepository userNativeRepository;

//    @Transactional
//    public void joinByEmail(UserRequest.JoinDTO reqDTO){
//        userNativeRepository.join(reqDTO);
//    }

    //로그인
    public User loginByName(UserRequest.LoginDTO reqDTO){
        User sessionUser = userNativeRepository.login(reqDTO);
        return sessionUser;
    }


    // 전문가(상담사 리스트)
    public List<UserResponse.ExpertUserDTO> getAllExpertUsers(){
        // 1. 모든 유저 찾기
        List<User> users = userRepository.findAll();

        // 2. userRole이 EXPERT인 유저만 필터링
        List<User> expertUsers = users.stream()
                .filter(user -> user.getUserRole() == UserRole.EXPERT)
                .collect(Collectors.toList());

        // 3. 전문가 타이틀가져오기

        return null;
    }

}
