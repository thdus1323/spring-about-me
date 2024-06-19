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



    public User 전문가상세보기(Integer expertId) {
        // 전문가 정보를 데이터베이스에서 가져옵니다.
        return userRepository.findById(expertId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid expert Id:" + expertId));
    }
}
