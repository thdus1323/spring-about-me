package com.example.aboutme.user;

import com.example.aboutme._core.utils.ApiUtil;
import com.example.aboutme.user.UserRequestRecord.UserProfileUpdateReqDTO;
import com.example.aboutme.user.UserResponseRecord.UserProfileUpdateRespDTO;
import com.example.aboutme.user.UserResponseRecord.expertFindDTO.FindWrapperRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserRestController {
    private final UserService userService;


    @GetMapping("/expert/search")
    public ResponseEntity<?> getUsersTimes(@RequestParam String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

        FindWrapperRecord findWrapperRecord = userService.getExpertFindBySearch(dateTime);
        System.out.println(findWrapperRecord);

        return ResponseEntity.ok(new ApiUtil(findWrapperRecord));
    }

    @PostMapping("/client/profiles")
    public ResponseEntity<?> updateProfile(@RequestBody UserProfileUpdateReqDTO request) {
        try {
            log.info("유저 프로필 업데이트 컨트롤러: {}", request);
            userService.updateUserProfile(request);
            return ResponseEntity.ok(new UserProfileUpdateRespDTO(true, "프로필이 저장되었습니다."));
        } catch (Exception e) {
            log.error("유저 프로필 업데이트 컨트롤러 에러 ", e);
            return ResponseEntity.status(500).body(new UserProfileUpdateRespDTO(false, "프로필 저장에 실패했습니다."));
        }
    }


}
