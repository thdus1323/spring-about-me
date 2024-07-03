package com.example.aboutme.comm;


import com.example.aboutme._core.utils.RedisUtil;
import com.example.aboutme.comm.enums.CommCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommRestController {

    private final CommService commService;
    private final RedisUtil redisUtil;

    @GetMapping("/api/comm")
    public ResponseEntity<CommResponse.ALLCommWithRepliesPageDTO> getAllCommWithReply(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "9") int size,
            @RequestParam(name = "category", required = false) CommCategory category) {

        Pageable pageable = PageRequest.of(page, size);
        CommResponse.ALLCommWithRepliesPageDTO respDTO = commService.findAllCommWithReply(pageable, category);
        return ResponseEntity.ok(respDTO);
    }
}
