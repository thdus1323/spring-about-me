package com.example.aboutme.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserNativeRepository {
    public final EntityManager em;

//    //회원가입
//    public void join(UserRequest.JoinDTO reqDTO){
//        Query query = em.createNativeQuery("insert into user_tb(email, password, password, name, created_at) values (?,?,?,?,now()");
//        query.setParameter(1, reqDTO.getEmail());
//        query.setParameter(2, reqDTO.getPassword());
//        query.setParameter(3, reqDTO.getPassword());
//        query.setParameter(4, reqDTO.getName());
//        query.executeUpdate();
//    }

    //로그인
    public User login(UserRequest.LoginDTO reqDTO) {
        Query query = em.createNativeQuery("select * from user_tb where email=? and password=? ", User.class);
        query.setParameter(1, reqDTO.getEmail());
        query.setParameter(2, reqDTO.getPassword());
        User sessionUser = (User) query.getSingleResult();
        return sessionUser;
    }
}
