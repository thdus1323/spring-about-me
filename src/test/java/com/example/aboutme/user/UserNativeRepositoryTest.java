//package com.example.aboutme.user;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//@DataJpaTest
//class UserNativeRepositoryTest {
//    @Autowired
//    private UserNativeRepositoryTest nativeRepositoryTest;
//
//    @Test
//    void login() {
//        UserRequest.LoginDTO loginDTO = new UserRequest.LoginDTO();
//        loginDTO.setEmail("mimi@nate.com");
//        loginDTO.setPassword("1234");
//        User user = nativeRepository.login(loginDTO);
//        System.out.println("user = " + user);
//    }
//}