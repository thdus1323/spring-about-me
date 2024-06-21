package com.example.aboutme.user;

import com.example.aboutme._core.utils.ApiUtil;
import com.example.aboutme.user.UserResponseDTO.expertFindDTO.FindWrapperRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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


}
