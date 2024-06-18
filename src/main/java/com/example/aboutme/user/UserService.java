package com.example.aboutme.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
