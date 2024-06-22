package com.example.aboutme.user;

import com.example.aboutme._core.config.RedisConfig;
import com.example.aboutme.comm.CommService;
import com.example.aboutme.user.UserResponseDTO.ClientMainDTO.ClientMainDTORecord;
import com.example.aboutme.user.UserResponseDTO.ExpertFindDetailDTO.DetailDTORecord;
import com.example.aboutme.user.UserResponseDTO.ExpertMainDTO.ExpertMainDTORecord;
import com.example.aboutme.user.UserResponseDTO.expertFindDTO.FindWrapperRecord;
import com.example.aboutme.user.enums.OauthProvider;
import com.example.aboutme.user.enums.UserRole;
//import com.example.aboutme.user.oauth.KakaoOAuthService;
//import com.example.aboutme.user.oauth.NaverOAuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final RedisTemplate<String, Object> redisTemp;
//    private final KakaoOAuthService kakaoOAuthService;
//    private final NaverOAuthService naverOAuthService;

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDTO, RedirectAttributes redirectAttributes) {
        User user = userService.loginByName(reqDTO);
        if (user == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "아이디 혹은 패스워드가 틀렸습니다.");
            return "redirect:/login";
        } else {
            SessionUser sessionUser = new SessionUser(user);
            redisTemp.opsForValue().set("sessionUser", sessionUser);
        }

        if (user.getUserRole() == UserRole.CLIENT) {
            return "redirect:/";
        } else if (user.getUserRole() == UserRole.EXPERT) {
            return "redirect:/experts/" + user.getId();
        } else {
            return "oauth/login";
        }
    }

    //    @PostMapping("/login")
//    public String login(UserRequest.LoginDTO reqDTO) {
//        SessionUser sessionUser = userService.loginByName(reqDTO);
//        if (sessionUser == null) {
//            throw new RuntimeException("아이디 혹은 패스워드가 틀렸습니다.");
//        } else {
//            redisTemp.opsForValue().set("sessionUser", sessionUser);
//        }
//
//        if (sessionUser.getUserRole() == UserRole.CLIENT) {
//            return "redirect:/";
//        } else if (sessionUser.getUserRole() == UserRole.EXPERT) {
//            return "redirect:/experts/" + sessionUser.getId();
//        } else {
//            return "oauth/login";
//        }
//    }

    @GetMapping("/redis/test")
    public @ResponseBody String redisTest() {
        SessionUser sessionUser = (SessionUser) redisTemp.opsForValue().get("sessionUser");
        System.out.println("sessionUser = " + sessionUser);
        return "redis test";
    }

    @GetMapping("/expert/reply")
    public String expertReply(Model model) {
        return "expert/expert-reply";
    }

//    @PostMapping("expert/reply-save")


    @GetMapping("/join")
    public String joinForm() {
        return "oauth/join";
    }


    @GetMapping("/login")
    public String loginForm() {
        return "oauth/login";
    }

    @PostMapping("/setUserRole")
    @ResponseBody
    public void setUserRole(@RequestParam("userRole") String userRoleStr) {
        redisTemp.opsForValue().set("userRole", userRoleStr);
    }

    @GetMapping("/oauth/callback/kakao")
    public String kakaoCallback(@RequestParam("code") String code) {
        SessionUser sessionUser = userService.loginKakao(code, redisTemp);
        redisTemp.opsForValue().set("sessionUser", sessionUser);
        return "redirect:/";
    }

    @GetMapping("/oauth/callback/naver")
    public String naverCallback(@RequestParam(value = "code") String code,
                                @RequestParam("state") String state) {
        SessionUser sessionUser = userService.loginNaver(code, state, redisTemp);
        redisTemp.opsForValue().set("sessionUser", sessionUser);
        return "redirect:/";
    }

//    @GetMapping("/oauth/callback/kakao")
//    public String kakaoCallback(@RequestParam("code") String code) {
//        User sessionUser = kakaoOAuthService.login(code, null, session);
//        session.setAttribute("sessionUser", sessionUser);
//
//        return "redirect:/";
//    }
//
//    @GetMapping("/oauth/callback/naver")
//    public String oauthNaverCallback(
//            @RequestParam(value = "code") String code,
//            @RequestParam("state") String state) {
//        User sessionUser = naverOAuthService.login(code, state, session);
//        session.setAttribute("sessionUser", sessionUser);
//
//        return "redirect:/";
//    }

//    @GetMapping("/oauth/callback/{provider}")
//    public String oauthCallback(
//            @PathVariable("provider") OauthProvider provider,
//            @RequestParam("code") String code,
//            @RequestParam(value = "state", required = false) String state,
//            HttpSession session) {
//
//        User sessionUser;
//
//        if (provider == OauthProvider.KAKAO) {
//            sessionUser = kakaoOAuthService.login(code, null, session);
//        } else if (provider == OauthProvider.NAVER) {
//            sessionUser = naverOAuthService.login(code, state, session);
//        } else {
//            throw new IllegalArgumentException("Unsupported OAuth provider: " + provider);
//        }
//
//        session.setAttribute("sessionUser", sessionUser);
//        return "redirect:/";
//    }




    @GetMapping("/logout")
    public String logout() {
        SessionUser sessionUser = (SessionUser) redisTemp.opsForValue().get("sessionUser");
        if (sessionUser != null) {
            redisTemp.delete("sessionUser");
            redisTemp.delete("userRole");
            // OAuth 로그아웃 처리
            boolean isLoggedOut = false;
            if (sessionUser.getProvider() == OauthProvider.KAKAO) {
                isLoggedOut = userService.logoutKakao(sessionUser.getAccessToken());
            } else if (sessionUser.getProvider() == OauthProvider.NAVER) {
                isLoggedOut = userService.logoutNaver(sessionUser.getAccessToken());
            }

            // OAuth 로그아웃 성공 시 Redis에서 세션 정보 삭제
            if (isLoggedOut) {
                redisTemp.delete("sessionUser");
                redisTemp.delete("userRole");
            }
        }
        return "redirect:/login";
    }


    // 👻👻👻공통👻👻👻
    // 클라이언트 메인페이지
    @GetMapping("/")
    public String index(Model model) {
        ClientMainDTORecord clientMain = userService.getClientMain();
        model.addAttribute("clientMain", clientMain);

        return "client/main";
    }

    // 익스퍼트 메인페이지
    @GetMapping("/experts/{expertId}")
    public String expertMain(Model model, @PathVariable Integer expertId) {
        ExpertMainDTORecord expertMain = userService.getExpertMain(expertId);
        model.addAttribute("expertMain", expertMain);

        return "expert/main";
    }


    // 🐯🐯🐯Client🐯🐯🐯
    //전문가 찾기 - 메인
    @GetMapping("/client/findExpert")
    public String findExpert(Model model) {
        FindWrapperRecord findWrapperRecord = userService.getExpertFind();
        model.addAttribute("expertList", findWrapperRecord);
        System.out.println(findWrapperRecord);
//        List<UserResponse.ExpertUserDTO> expertUserList = userService.getAllExpertUsers();
//        session.setAttribute("expertUserList", expertUserList);

        return "client/findExpert/main";
    }


    // 전문가 찾기 - 상세보기
    @GetMapping("/client/findExpert/detail/{expertId}")
    public String findExpertDetail(Model model, @PathVariable("expertId") Integer expertId) {
        DetailDTORecord detailDTORecord = userService.getFindExpertDetails(expertId);
        model.addAttribute("model", detailDTORecord);
        return "client/findExpert/detail";
    }

    //클라이언트 - 마이페이지
    @GetMapping("/client/mypage")
    public String clientMypage() {
        SessionUser sessionUser = (SessionUser) redisTemp.opsForValue().get("sessionUser");
        if (sessionUser == null) {
            return "oauth/login";
        } else {
            return "client/mypage";
        }
    }

    //익스퍼트 - 마이페이지
    @GetMapping("/expert/mypage")
    public String expertMypage() {
        return "expert/mypage";
    }
    // 🩺🩺🩺expert🩺🩺🩺
}
