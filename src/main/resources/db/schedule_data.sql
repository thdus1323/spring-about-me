
-- 스케줄 테이블
INSERT INTO schedule_tb (expert_id, day_of_week, start_time, end_time, created_at, updated_at)
VALUES
    -- 21번 스케줄: 주중 근무 시간
    (21, 'MONDAY', '09:00', '18:00', NOW(), NOW()),
    (21, 'TUESDAY', '09:00', '18:00', NOW(), NOW()),
    (21, 'WEDNESDAY', '09:00', '18:00', NOW(), NOW()),
    (21, 'THURSDAY', '09:00', '18:00', NOW(), NOW()),
    (21, 'FRIDAY', '09:00', '18:00', NOW(), NOW()),

    -- 22번 스케줄: 월요일만 근무 시간
    (22, 'MONDAY', '10:00', '17:00', NOW(), NOW()),

    -- 24번 스케줄: 수요일만 근무 시간
    (24, 'WEDNESDAY', '10:00', '14:00', NOW(), NOW());