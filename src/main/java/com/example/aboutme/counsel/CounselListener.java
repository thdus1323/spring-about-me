package com.example.aboutme.counsel;

import com.example.aboutme.alarm.sse.SseService;
import com.example.aboutme.counsel.enums.ReservationStatus;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class CounselListener {

    private static final Logger logger = LoggerFactory.getLogger(CounselListener.class);
    private static SseService sseService;

    @Autowired
    public void setSseService(SseService sseService) {
        CounselListener.sseService = sseService;
    }

    @PostUpdate
    public void onPostUpdate(Counsel counsel) {
        logger.info("Counsel entity updated: {}", counsel.getId());
        ReservationStatus status = counsel.getReservationStatus();
        logger.info("Updated status: {}", status);
        switch (status) {
            case RESERVATION_SCHEDULED:
                sseService.notifyExpert(counsel.getExpert().getId(), "예약신청이 도착했습니다.");
                break;
            case RESERVATION_COMPLETED:
                sseService.notifyClient(counsel.getClient().getId(), "예약이 확정되었습니다.");
                break;
            // 필요한 다른 상태 변경 시 추가 로직
        }
    }
}
