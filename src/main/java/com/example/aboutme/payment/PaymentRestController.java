package com.example.aboutme.payment;
import com.example.aboutme._core.utils.RedisUtil;
import com.example.aboutme.user.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class PaymentRestController {

    private final PaymentService paymentService;
    private final RedisUtil redisUtil;

    @PostMapping("/client/mypage/cancelPayment")
    public ResponseEntity<?> cancelPayment(@RequestBody Map<String, String> request) {
        SessionUser sessionUser = redisUtil.getSessionUser();
        String impUid = request.get("impUid");
        try {
            Map<String, Object> cancelResponse = paymentService.cancelPayment(impUid, sessionUser);
            return ResponseEntity.ok(Map.of("success", true, "response", cancelResponse));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("success", false, "message", e.getMessage()));
        }
    }
}
